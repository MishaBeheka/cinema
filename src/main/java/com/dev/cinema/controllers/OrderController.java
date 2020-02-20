package com.dev.cinema.controllers;

import com.dev.cinema.dto.OrderRequestDto;
import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(ShoppingCartService shoppingCartService,
                           UserService userService,
                           OrderService orderService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping(value = "/orders/complete")
    public Order completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return buildOrder(orderRequestDto);
    }

    @GetMapping(value = "/orders")
    public List<OrderResponseDto> getAllOrders(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.getById(userId))
                .stream().map(this::buildOrderResponseDto).collect(Collectors.toList());
    }

    private Order buildOrder(OrderRequestDto orderRequestDto) {
        User user = userService.getById(orderRequestDto.getUserId());
        ShoppingCart shoppingCart =
                shoppingCartService.getByUser(user);
        return orderService.completeOrder(shoppingCart.getTickets(), user);
    }

    private OrderResponseDto buildOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setTickets(order.getTickets());
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        return orderResponseDto;
    }
}
