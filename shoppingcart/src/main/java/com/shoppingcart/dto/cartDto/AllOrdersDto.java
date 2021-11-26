package com.shoppingcart.dto.cartDto;

import com.shoppingcart.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllOrdersDto {

    private int OrderId;

    private Date date;

    private List<OrderItem> orderItems ;

    private Double totalPrice;
}
