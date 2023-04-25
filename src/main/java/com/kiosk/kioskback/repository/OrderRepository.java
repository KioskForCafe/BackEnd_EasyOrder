package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
    
}
