package com.springboot.member.controller;

import com.springboot.member.dto.MemberPageResponseDto;
import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.member.mapper.MemberMapper;
import com.springboot.member.service.MemberService;
import com.springboot.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;


/**
 * - DI 적용
 * - Mapstruct Mapper 적용
 * - @ExceptionHandler 적용
 */
@RestController
@RequestMapping("/v10/members")
@Validated
@Slf4j
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/v10/members";
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        Member member = mapper.memberPostDtoToMember(memberDto);

        Member resultMember = memberService.createMember(member);

        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, resultMember.getMemberId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        Member response =
                memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),
                HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member response = memberService.findMember(memberId);
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response)
                , HttpStatus.OK);
    }

    @GetMapping
    // 페이지 번호와 페이지 사이즈를 파라미터로 넣는다. 1이상 숫자이여야 하기에 @Positive 사용
    public ResponseEntity<MemberPageResponseDto> getMembers(@Positive @RequestParam("page") int page,
                                                            @Positive @RequestParam("size") int size) {

        // 클라이언트는 1이지만 Spring Data JPA는 페이지 번호가 0부터 시작하므로 -1 해준다.
        Page<Member> memberPage = memberService.findMembers(page, size);

        // 페이지 정보 생성
        MemberPageResponseDto.PageInfo pageInfo = MemberPageResponseDto.PageInfo.builder()
                .page(page) // 페이지 번호
                .size(size) // 한페이지 회원수
                .totalElements((int)memberPage.getTotalElements()) // 전체 회원수
                .totalPages(memberPage.getTotalPages()) // 전체 페이지 수
                .build();

        // 회원 정보를 DTO 형태로 변환
        List<Member> memberList = memberPage.getContent();
        List<MemberResponseDto> response = mapper.membersToMemberResponseDtos(memberList);

        // 페이지 정보와 회원 리스트를 포함한 응답 생성
        MemberPageResponseDto responseDto = new MemberPageResponseDto(response, pageInfo);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
