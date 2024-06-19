package com.springboot.member.mapper;

import com.springboot.member.dto.*;
import com.springboot.member.entity.Member;
import com.springboot.member.service.MemberService;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

//    default MembersResponseDto membersToMembersResponseDto(MemberService memberService, List<Member> members, int page, int size){
//        List<MemberResponseDto> memberResponseDtos = membersToMemberResponseDtos(members);
//        PageInfoResponseDto pageInfoResponseDto = PageInfoResponseDto.builder()
//                .page(page + 1)
//                .size(size)
//                .totalElements( memberService.memberCount())
//                .totalPages((int)Math.ceil((double) memberService.memberCount() / size))
//                .build();
//
//        MembersResponseDto membersResponseDto = MembersResponseDto.builder()
//                .data(memberResponseDtos)
//                .pageInfo(pageInfoResponseDto)
//                .build();
//
//        return membersResponseDto;
//    }

}
