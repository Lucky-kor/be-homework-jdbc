package com.springboot.pageresponsedto;
import com.springboot.member.dto.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto<T> {
    private List<T> data;

    private PageInfo pageInfo;

}
