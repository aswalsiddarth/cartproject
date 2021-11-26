package com.shoppingcart.repository;

import com.shoppingcart.model.Order;
import com.shoppingcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

    List<Order> findAllByUser(User user);

}
