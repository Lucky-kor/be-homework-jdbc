package com.springboot.member.repository;

import com.springboot.member.entity.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

// TODO 페이지네이션을 적용하세요!
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
////찾는다 모든 오더를 멤버 아이디로 순서대로 페이징으로 페이저블을 담아서
//    Page<Member> findAllByOrderByMemberIdDesc(Pageable pageable);
}
