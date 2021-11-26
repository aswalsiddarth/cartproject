package com.shoppingcart.dto.cartDto;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartDto {

    private @NotNull int userId;

    private @NotNull int productId;

    private @NotNull int quantity;


}
