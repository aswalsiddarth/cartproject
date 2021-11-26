package com.shoppingcart.service.impl;


import com.shoppingcart.dto.cartDto.*;
import com.shoppingcart.exception.CustomException;
import com.shoppingcart.model.Order;
import com.shoppingcart.model.OrderItem;
import com.shoppingcart.model.User;
import com.shoppingcart.dto.cartDto.AllOrdersDto;
import com.shoppingcart.repository.OrderItemRepo;
import com.shoppingcart.repository.OrderRepo;
import com.shoppingcart.repository.UserRepo;
import com.shoppingcart.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    private final OrderItemRepo orderItemRepo;

    private final UserRepo userRepo;

    private final CartServiceImpl cartService;


    @Override
    public OrderDto orderItems (UserIdDto orderRequest) throws CustomException {

        User user= userRepo.getById(orderRequest.getId());
        CartDto cartDto = cartService.listCartItems(user);

        if(cartDto.getTotalCost()==0){
            throw new CustomException("no items in the cart");
        }

        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
        Order order = new Order();
        order.setDate(new Date(System.currentTimeMillis()));
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalCost());
        orderRepo.save(order);

        for(CartItemDto cartItemDto : cartItemDtoList) {

            OrderItem orderItem = new OrderItem();
            orderItem.setDate(order.getDate());
            orderItem.setPrice(cartItemDto.getProduct().getPrice());
            orderItem.setProduct(cartItemDto.getProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(order);
            orderItemRepo.save(orderItem);
        }

        cartService.deleteUserCartItems((user));
        OrderDto orderDto = new OrderDto();
        orderDto.setDate(order.getDate());
        orderDto.setMessage("Successfully Ordered");
        orderDto.setSuccess("true");

        return orderDto;
    }


    @Override
    public List<AllOrdersDto> listOrders(int id)  {

        User user= userRepo.getById(id);

        List<Order> allOrder = orderRepo.findAllByUser(user);
        List<AllOrdersDto> allOrders = new ArrayList<>();

        for(Order order : allOrder) {

            AllOrdersDto allOrdersDto = new AllOrdersDto();
            allOrdersDto.setOrderId(order.getId());
            allOrdersDto.setOrderItems(order.getOrderItems());
            allOrdersDto.setTotalPrice(order.getTotalPrice());
            allOrdersDto.setDate(order.getDate());
            allOrders.add(allOrdersDto);
        }
        return allOrders;

    }
}


