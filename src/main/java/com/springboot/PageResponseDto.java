package com.springboot;

import com.springboot.member.page.PageInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PageResponseDto<T>{
 private List<T> data;
 private PageInfo pageInfo;

 public PageResponseDto(List<T> data, PageInfo pageInfo){
     this.data = data;
     this.pageInfo = pageInfo;
 }
}
