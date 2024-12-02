package com.springboot.coffee.repository;
//괴도왔다감
import com.springboot.coffee.entity.Coffee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long>, PagingAndSortingRepository<Coffee, Long> {
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    @Query("SELECT * FROM COFFEE WHERE COFFEE_ID = :coffeeId")
    Optional<Coffee> findByCoffee(Long coffeeId);
}
