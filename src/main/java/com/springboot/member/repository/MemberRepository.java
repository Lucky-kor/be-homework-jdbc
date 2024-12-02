package com.springboot.member.repository;

import com.springboot.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

// TODO 페이지네이션을 적용하세요!
public interface MemberRepository extends CrudRepository<Member, Long> , PagingAndSortingRepository<Member, Long> {
    // 메서드 이름으로 추론함. findAll 을 할 거다. Order by 정렬. member id desc 내림차순으로.
    // 구현체가 쿼리문을 만들어서 Repository 로 보냄.
    // 이거를 페이지 객체 안에 담아서 보내주는 것.
//    Page<Member> findAllByOrderByMemberIdDesc (Pageable pageable);
    // FindAll 에서 인터페이스 다중 상속이 가능함.
    Optional<Member> findByEmail(String email);
}
