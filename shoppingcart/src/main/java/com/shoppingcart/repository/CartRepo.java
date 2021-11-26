package com.shoppingcart.repository;

import com.shoppingcart.model.Cart;
import com.shoppingcart.model.CartKey;
import com.shoppingcart.model.Product;
import com.shoppingcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, CartKey> {

    List<Cart> findAllByUser(User user);

    void deleteByUser(User user);

    Cart findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);




}
