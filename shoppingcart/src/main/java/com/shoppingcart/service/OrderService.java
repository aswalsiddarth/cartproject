package com.shoppingcart.service;


import com.shoppingcart.dto.cartDto.AllOrdersDto;
import com.shoppingcart.dto.cartDto.OrderDto;
import com.shoppingcart.dto.cartDto.UserIdDto;

import java.util.List;

public interface OrderService {

    OrderDto orderItems(UserIdDto orderRequest);

    List<AllOrdersDto> listOrders(int id);
}
