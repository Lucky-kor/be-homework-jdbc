package com.springboot.member.controller;

import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.PageInfo;
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

    /*@GetMapping
    public ResponseEntity getMembers(@RequestParam @Positive int pageNumber, @RequestParam @Positive int pageSize) {
        // TODO 페이지네이션을 적용하세요!
        // 페이지 객체 생성
        Page page = Page.builder()
                .pageNumber(pageNumber).pageSize(pageSize)
                .build();
        // 페이지 정보 전달하여 가져오기
        List<Member> members = memberService.findMembers(page);
        int totalElements = memberService.getTotalElements();
        PageInfo pageInfo = PageInfo.builder()
                .page(pageNumber)
                .size(pageSize)
                .totalElements(totalElements)
                .totalPages(totalElements / pageSize + 1)
                .build();
                *//*new PageInfo(pageNumber,pageSize,totalElements,totalElements / pageSize + 1);*//*
        MemberResponseDto responseDto = MemberResponseDto.builder()
                .data(members)
                .pageInfo(pageInfo)
                .build();*//*new MemberResponseDto(members, pageInfo);*//*

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }*/
    @GetMapping
    public ResponseEntity getMembers(@RequestParam @Positive int pageNumber,
                                     @RequestParam @Positive int pageSize){
        Page<Member> memberPage = memberService.findMembers(pageNumber-1, pageSize);
        PageInfo pageInfo = PageInfo.builder()
                .page(pageNumber)
                .size(pageSize)
                .totalElements(memberService.getTotalElements())
                .totalPages(memberPage.getTotalPages())
                .build();/*new PageInfo(pageNumber,pageSize, (int) memberPage.getTotalElements(), memberPage.getTotalPages());*/
        List<Member> members = memberPage.getContent();
        return new ResponseEntity<>(new MemberResponseDto(members, pageInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
