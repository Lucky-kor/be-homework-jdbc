package com.springboot.coffee.mapper;

import com.springboot.PageInfo;
import com.springboot.coffee.dto.CoffeePatchDto;
import com.springboot.coffee.dto.CoffeePostDto;
import com.springboot.coffee.dto.CoffeeResponseDto;
import com.springboot.coffee.dto.CoffeePageResponseDtos;
import com.springboot.coffee.entity.Coffee;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);
    default CoffeePageResponseDtos CoffeesToCoffeePageResponseDtos(Page<Coffee>coffees){
        PageInfo pageInfo=new PageInfo(coffees.getNumber(),
                coffees.getSize(),
                coffees.getNumberOfElements(),
                coffees.getTotalPages());
       return new CoffeePageResponseDtos(coffees.getContent(),pageInfo);
    }

}
