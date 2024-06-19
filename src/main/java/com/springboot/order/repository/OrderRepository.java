package com.springboot.order.repository;

import com.springboot.coffee.entity.Coffee;
import com.springboot.order.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
}
