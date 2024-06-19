package com.springboot.coffee.mapper;

import com.springboot.response.PageInfo;
import com.springboot.coffee.dto.CoffeePatchDto;
import com.springboot.coffee.dto.CoffeePostDto;
import com.springboot.coffee.dto.CoffeeResponseDto;
import com.springboot.coffee.entity.Coffee;
import com.springboot.response.PageResponseDtos;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    //List<CoffeeResponseDto> coffeesToPageResponseDtos(List<Coffee> coffees);

    default PageResponseDtos CoffeesToPageResponseDtos(Page<Coffee>coffees){
        PageInfo pageInfo=new PageInfo(coffees.getNumber()+1,
                coffees.getSize(),
                coffees.getNumberOfElements(),
                coffees.getTotalPages());
       return new PageResponseDtos(coffees.getContent(),pageInfo);
    }

}
