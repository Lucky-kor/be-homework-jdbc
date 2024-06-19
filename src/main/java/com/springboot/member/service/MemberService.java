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

    //list 가 아니고, page 로 바꿔야 함.
    public Page<Member> findMembers(int page, int size) {
        // TODO 페이지네이션을 적용하세요!
        // pageRequest 에 의해 pageable 에 페이징 정보가 담겨 객체화된다.
        // ㅔ뮫ㅁ힏 dl repositry 가 상속된 인터페이스의 메서드에 파라미터로 전달된다.
        // return으로 page<>가 전달된다.
        // 전달된 page<> 에 담겨진 page 정보를 바탕으로 로직을 처리하면 된다.

        // PageRequest 는 구현체임....!!! 여기에 Page, size 를 넣으면, 이 기준으로 페이지네이션이 동작함.
//        PageRequest pageRequest = PageRequest.of(page, size);
        // pageable 이 상위에 있기 때문에 타입으로 받을 수 있음.
        //
        Pageable pageable = PageRequest.of(page, size, Sort.by("memberId").descending());

        // pageRequest 구현체를 Pageable 에 받음.
        return memberRepository.findAll(pageable);
    }
    //repository

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
