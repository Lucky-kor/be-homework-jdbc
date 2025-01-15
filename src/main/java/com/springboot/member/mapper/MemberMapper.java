package com.springboot.member.mapper;

import com.springboot.member.dto.MemberPageResponseDto;
import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
//    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

    default MemberPageResponseDto pageToMemberPageResponseDto(Page<Member> page){
        List<MemberResponseDto> memberResponseDtos = page.stream()
                .map(member -> MemberResponseDto.builder()
                        .memberId(member.getMemberId())
                        .name(member.getName())
                        .phone(member.getPhone())
                        .email(member.getEmail())
                        .build()
                )
                .collect(Collectors.toList());

        MemberPageResponseDto.PageInfo pageInfo = MemberPageResponseDto.PageInfo.builder()
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();

        return MemberPageResponseDto.builder().pageInfo(pageInfo).data(memberResponseDtos).build();
    }
}
