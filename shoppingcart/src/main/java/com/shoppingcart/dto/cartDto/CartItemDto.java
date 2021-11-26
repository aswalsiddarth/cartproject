package com.shoppingcart.dto.cartDto;

import com.shoppingcart.model.Product;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private @NotNull Integer quantity;

    private @NotNull Product product;
}
