package com.springboot.order.controller;

import com.springboot.coffee.service.CoffeeService;
import com.springboot.order.dto.OrderPageResponseDtos;
import com.springboot.order.dto.OrderPostDto;
import com.springboot.order.dto.OrderResponseDto;
import com.springboot.order.entity.Order;
import com.springboot.order.mapper.OrderMapper;
import com.springboot.order.service.OrderService;
import com.springboot.utils.UriCreator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v10/orders")
@Validated
public class OrderController {
    private final static String ORDER_DEFAULT_URL = "/v10/orders";
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final CoffeeService coffeeService;

    public OrderController(OrderService orderService,
                           OrderMapper mapper,
                           CoffeeService coffeeService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.coffeeService = coffeeService;
    }

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDto));
        URI location = UriCreator.createUri(ORDER_DEFAULT_URL, order.getOrderId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order order = orderService.findOrder(orderId);
        return new ResponseEntity<>(mapper.orderToOrderResponseDto(coffeeService, order),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders(@RequestParam(name="page",defaultValue ="1")int page,
                                    @RequestParam(name="size",defaultValue ="1" )int size,
                                    @RequestParam(defaultValue = "orderId")String sortBy,
                                    @RequestParam(defaultValue = "DSEC")String sortDirection) {
        Pageable pageable = PageRequest.of(page,size);
        //Sort sort = Sort.by(sortDirection,sortBy);
        //Set<Order> orders = orderService.findOrders(pageable,sort);

//        List<OrderResponseDto> response =
//                orders.stream()
//                        .map(order -> mapper.orderToOrderResponseDto(coffeeService, order))
//                        .collect(Collectors.toList());
            OrderPageResponseDtos orderPageResponseDto = mapper
                    .orderToOrderPageResponseDtos(orderService.findOrders(pageable));
        return new ResponseEntity<>(orderPageResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
