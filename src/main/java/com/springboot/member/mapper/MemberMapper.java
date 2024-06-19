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

    //    Repository 계층에서 받은 받은 Page<Member> 와 PageResponseDto의 매퍼구현

    default PageResponseDto memberPageToPageResponseDto(Page<Member> members) {
        return PageResponseDto.of(
                membersToMemberResponseDtos(members.getContent()),
                members
        );

//    Repository 계층에서 받은 Page<Member> 객체를 매개변수로
//    PageResponseDto를 생성하는 of메서드를 호출 -> 매개변수로 Repository 받아올 객체와 Page
//    -> 이때 생성자로 이너클래스인 PageInfo가 생성 -> page의 숫자, 사이즈, 요소- 보여줄 개수 ,전체페이지가 들어있음
//  1번 매개변수  .getContent()로 List로 만들어줌 - > 상속받은 클래스의 메서드임 -> ResponseDto에는 List로 보내주기위함
//    2번 매개변수는 Page<Member>의 page정보로 만들어 주겠다.
    }
}
