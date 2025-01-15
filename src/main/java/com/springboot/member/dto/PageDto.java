package com.springboot.member.dto;

import lombok.Builder;
import lombok.Getter;

//MemberPageResponseDto를 coffee, order도 모두 사용할 수있도록 제네릭타입으로 작성
@Getter
@Builder
public class PageDto<T> {

}
