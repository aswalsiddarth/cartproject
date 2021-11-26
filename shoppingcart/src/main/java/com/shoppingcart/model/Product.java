package com.shoppingcart.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

     @Id
     @GeneratedValue(strategy =  GenerationType.IDENTITY)
     private int id;

     private String name;

     private double price;

     private String description;

     private int quantity;

     @JsonIgnore
     @ManyToOne
     @JoinColumn(name = "category_id")
     private Category category;

     @JsonIgnore
     @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
     private List<Cart> carts;

}
