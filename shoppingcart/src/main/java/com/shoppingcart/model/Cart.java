package com.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @EmbeddedId
    private CartKey cartId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;


    @JsonIgnore
    @MapsId("userId")
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    private  int quantity;

}
