package com.edu.order.repository;

import com.edu.order.domain.Order;
import com.edu.order.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyer(User buyer);
    @Query(value = "select distinct o from UserOrder o , UserOrderDetail ud where ud.product.seller = ?1 ")
    List<Order> findBySeller(User seller);
}
