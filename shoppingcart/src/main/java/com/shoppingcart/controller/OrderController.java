package com.shoppingcart.controller;


import com.shoppingcart.dto.cartDto.OrderDto;
import com.shoppingcart.dto.cartDto.UserIdDto;
import com.shoppingcart.dto.cartDto.AllOrdersDto;
import com.shoppingcart.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping("/place-order")
    public ResponseEntity<OrderDto> orderItems(@RequestBody UserIdDto orderRequestDto) {

        OrderDto orderDto = orderService.orderItems(orderRequestDto);

        return new ResponseEntity<>(orderDto,HttpStatus.CREATED);

    }

   @GetMapping("/")
    public ResponseEntity<List<AllOrdersDto>> getAllItems(@RequestParam int userId){

     List<AllOrdersDto> listOrders = orderService.listOrders(userId);

     return new ResponseEntity<>(listOrders  ,HttpStatus.OK);

    }

}
