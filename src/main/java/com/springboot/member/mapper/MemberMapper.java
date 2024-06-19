package com.springboot.member.mapper;

import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.page.PageInfo;
import com.springboot.page.PageResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

//    default MemberPageResponseDto memberPageToMemberResponseDtos(List<Member> members, int page, int size, long total){
//        MemberPageResponseDto MemberPageResponseDto = com.springboot.member.dto.MemberPageResponseDto
//                .builder()
//                .data(membersToMemberResponseDtos(members))
//                .pageInfo(new PageInfo(page, size, total, (int)(total % size == 0 ? total / size : total / size + 1)))
//                .build();
//        return MemberPageResponseDto;
//    };

    default PageResponseDto memberPageToMemberResponseDtos(List<Member> members, int page, int size, long total){
        PageResponseDto MemberPageResponseDto = PageResponseDto
                .builder()
                .data(membersToMemberResponseDtos(members))
                .pageInfo(new PageInfo(page, size, total, (int)(total % size == 0 ? total / size : total / size + 1)))
                .build();
        return MemberPageResponseDto;
    };
}
