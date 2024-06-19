package com.springboot.coffee.mapper;

import com.springboot.coffee.dto.CoffeePatchDto;
import com.springboot.coffee.dto.CoffeePostDto;
import com.springboot.coffee.dto.CoffeeResponseDto;
import com.springboot.coffee.entity.Coffee;
import com.springboot.page.PageInfo;
import com.springboot.page.PageResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);

    default PageResponseDto coffeePageToCoffeeResponseDtos(List<Coffee> coffees, int page, int size, long total){
        PageResponseDto MemberPageResponseDto = PageResponseDto
                .builder()
                .data(coffeesToCoffeeResponseDtos(coffees))
                .pageInfo(new PageInfo(page, size, total, (int)(total % size == 0 ? total / size : total / size + 1)))
                .build();
        return MemberPageResponseDto;
    };
}
