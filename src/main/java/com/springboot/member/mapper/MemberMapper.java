package com.springboot.member.mapper;

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
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
    //페이지네이션 기능 적용 -> Page<T>으로 메서드 "오버로딩"
//    Page<MemberResponseDto> membersToMemberResponseDtos(Page<Member> members);
//오버로딩이 인식이 안됨
    default Page<MemberResponseDto> membersToMemberResponseDtos(Page<Member> members) {
        return members.map(member -> memberToMemberResponseDto(member));
    }
    default List<MemberResponseDto> pageToList(Page<MemberResponseDto> dtos) {
        return dtos.stream().collect(Collectors.toList());
    }
}
