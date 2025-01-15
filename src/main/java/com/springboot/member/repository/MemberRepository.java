package com.springboot.member.repository;

import com.springboot.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

//PagingAndSortingRepository
//public interface MemberRepository extends CrudRepository<Member, Long> {
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

//    memberId를 기준으로 내림차순(최신순) 모든 정보를 가져온다.
//    매개변수로 Pageable 타입의 객체를 넘겨주면 객체의 정보를 읽고 page 조건을 설정해 데이터를 가져온다.
//    Page<Member> findAllByOrderByMemberIdDesc(Pageable pageable);
}
