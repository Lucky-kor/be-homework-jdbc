package com.springboot.order.repository;

import com.springboot.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Page<Order> findAll(Pageable pageable);
}
