package com.shoppingcart.service;

import com.shoppingcart.dto.cartDto.AddToCartDto;
import com.shoppingcart.dto.cartDto.CartDto;
import com.shoppingcart.model.User;

public interface CartService {


    CartDto listCartItems(User user);

    void addToCart(AddToCartDto addToCartDto);

    void updateCartItem(AddToCartDto addToCartDto);

    void deleteUserCartItems(int id, int userId);

    void deleteUserCartItems(User user);

}
