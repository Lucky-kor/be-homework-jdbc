package com.springboot.member.entity;

import com.springboot.member.dto.MemberPostDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

/**
 *  - 멤버 변수 추가
 *  - lombok 추가
 *  - Spring Data JDBC 엔티티 설정 추가
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    private Long memberId;

    private String email;

    private String name;

    private String phone;



}
