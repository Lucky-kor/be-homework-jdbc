package com.springboot.member.service;

import com.springboot.MyPage;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Member> findMembers(MyPage myPage) {
        // TODO 페이지네이션을 적용하세요!
        // 전체 멤버 목록
        ArrayList<Member> allMembers = (ArrayList<Member>) memberRepository.findAll();
        // subList로 자르기
        ArrayList<Member> pagedMembers;
        if(allMembers.size() >= myPage.getStartIndex()){
            int from = myPage.getStartIndex();
            int tempTo = from + myPage.getPageSize();
            int to = Math.min(allMembers.size(), tempTo);
            pagedMembers = new ArrayList<>(allMembers.subList(from, to));
        }else{
            throw new BusinessLogicException(ExceptionCode.PAGE_NOT_FOUND);
        }
        // 내림차순 전환
        Collections.reverse(pagedMembers);
        return pagedMembers;
    }
    public Page<Member> findMembers(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return memberRepository.findAllByOrderByMemberIdDesc(pageRequest);
    }
    public int getTotalElements(){
        ArrayList<Member> allMembers = (ArrayList<Member>) memberRepository.findAll();
        return allMembers.size();
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
