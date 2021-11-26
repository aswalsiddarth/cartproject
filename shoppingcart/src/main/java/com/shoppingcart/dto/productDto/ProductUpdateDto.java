package com.shoppingcart.dto.productDto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDto {

    private @NotNull int productId;
    private @NotNull int quantity;
}
