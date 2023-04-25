package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer>{
    
}
