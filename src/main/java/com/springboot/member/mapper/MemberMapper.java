package com.springboot.member.mapper;

import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.dto.PageResponseDto;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

    default PageResponseDto PageToPageResponseDto(Page<Member> memberPage) {
        List<MemberResponseDto> memberResponseDtos = membersToMemberResponseDtos(memberPage.getContent());

        PageResponseDto.PageInfo pageInfo = PageResponseDto.PageInfo.builder()
                .page(memberPage.getNumber() + 1) // 0-based index이므로 +1
                .size(memberPage.getSize())
                .totalElements(memberPage.getTotalElements())
                .totalPages(memberPage.getTotalPages())
                .build();

        return PageResponseDto.builder()
                .data(memberResponseDtos)
                .pageInfo(pageInfo)
                .build();
    }
}
