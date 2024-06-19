package com.springboot.order.repository;

import com.springboot.order.entity.Order;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    Page<Order> findAllByOrderByOrderId(Pageable pageable);
   // Set<Order> findAll(Sort sort);
}
