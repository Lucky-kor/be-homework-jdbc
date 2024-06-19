package com.springboot.member.mapper;

import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.response.PageInfo;
import com.springboot.response.PageResponseDtos;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    //List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

    default PageResponseDtos membersToPageResponseDtos(Page<Member>members){
        //members.getContent() => List<Member>
      /*    MemberPageResponseDto memberPageResponseDto =
                 new MemberPageResponseDto(members.getContent(),new PageInfo());
       MemberPageResponseDto memberPageResponseDto= members.getContent().stream().map(member ->
                MemberPageResponseDto.builder()
                        .data(member.getMemberId()
                                ,member.getEmail()
                                ,member.getName()
                                ,member.getPhone()))
                        .pageInfo(new PageInfo())
                        .build();
     */
        PageInfo pageInfo = new PageInfo(
                members.getNumber()+1,
                members.getSize(),
                members.getNumberOfElements(),
                members.getTotalPages());
        return new PageResponseDtos(members.getContent(),pageInfo);
    }
}
