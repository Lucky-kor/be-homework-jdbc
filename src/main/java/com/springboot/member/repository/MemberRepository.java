package com.springboot.member.repository;

import com.springboot.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.regex.Pattern;

// TODO 페이지네이션을 적용하세요!
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Page<Member> findAllByOrderByMemberIdDesc(Pageable pagaeble); // 페이지에 대한 정보들을 담고있는 객체

}
