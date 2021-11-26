package com.shoppingcart.model;


import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@EqualsAndHashCode
public class CartKey implements Serializable {

    private int userId;

    private int productId;
}
