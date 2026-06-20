package com.example.zomato.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zomato.Entity.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer>{

}
