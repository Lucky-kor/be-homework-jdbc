package com.springboot.member.controller;

import com.springboot.response.PageInfo;
import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.response.MultiResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.member.mapper.MemberMapper;
import com.springboot.member.service.MemberService;
import com.springboot.response.SingleResponseDto;
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
        // 데이터를 SingleResponseDto 에 덧대서 data 로 감싸 받을 수 있도록 함.
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(response))
                , HttpStatus.OK);
    }

    @GetMapping
                                     // 쿼리 파라미터, 쿼리 스트링. 혼영해서 받을 수 있음.
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        // TODO 페이지네이션을 적용하세요!
        // page 1 페이지를 주세요 ! 하면 controller 에서는 0으로 받아서 관리해야 함.
        // 1번째 페이지가 0번 인덱스 같은 느낌.
        Page<Member> memberPage = memberService.findMembers(page -1, size);
        List<Member> members = memberPage.getContent();
        List<MemberResponseDto> response = mapper.membersToMemberResponseDtos(members);
        PageInfo pageInfo = new PageInfo(page, size, memberPage.getNumberOfElements(), memberPage.getTotalPages() );
        // pageResponseDto 라는 틀 안에 resp
        return new ResponseEntity<>(new MultiResponseDto(response, pageInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
