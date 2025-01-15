package com.springboot.member.repository;

import com.springboot.member.entity.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

// TODO 페이지네이션을 적용하세요!
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
