package com.shoppingcart.dto.productDto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private int id;

    private @NotNull String name;

    private  @NotNull int  price;

    private String description;

    private @NotNull Integer  categoryId;

    private @NotNull Integer quantity;

}
