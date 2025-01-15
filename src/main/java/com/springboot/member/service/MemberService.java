package com.springboot.member.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * V2
 *  - 메서드 구현
 *  - DI 적용
 *  - Spring Data JDBC 적용
 */
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member createMember(Member member) {
        // 이미 등록된 이메일인지 확인
        verifyExistsEmail(member.getEmail());

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());


        // TODO 리팩토링 포인트
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));

        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    //Page객체를 반환해야한다. Member를 보여줘야해
    public Page<Member> findMembers(int page, int size ) {
        // TODO 페이지네이션을 적용하세요!
        //Pageable 객체 부르기 왜? -> findAll() 을 사용하려면 param으로 Pageable객체를 받아야해서 순차적으로 만드는 중입니다.
        //PageRequest : 크기 지정 (몇개를 보여줄지) , 오버로딩
        //PageRequest 가 갖고있는 int page = URI를 보내면 페이지 넘버가 나옴, 요청받은 패이지 넘버 : 파라미터로 받아야해
        //page -1 로 작성 : Page 객체나 배열의? 형태로 가지고 있어서 인덱스를 불러야한다.
        //size도 사용자가 지정할 수 있도록 파람으로 받음
        //descending : 내림차순
        Pageable pageable = PageRequest.of(page -1, size, Sort.by("memberId").descending());
        Page<Member> memberPages = memberRepository.findAll(pageable);

        return memberPages;
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
