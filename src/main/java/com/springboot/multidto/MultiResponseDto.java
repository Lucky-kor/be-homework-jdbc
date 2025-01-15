package com.springboot.multidto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

// 타입을 제한하기 위해서 제네릭을 사용한다.
// 타입 매개변수는 List에 쓰이며, 타입매개변수만 사용하여도 반환은 무조건 list로 반환한다.
@Getter
//AllArgsConstructor
@NoArgsConstructor
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1, page.getSize(),
                page.getTotalElements(), page.getTotalPages());
    }
}
