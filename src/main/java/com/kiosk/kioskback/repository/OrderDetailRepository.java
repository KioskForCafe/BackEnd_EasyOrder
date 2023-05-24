package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.OrderDetailEntity;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer>{

    public OrderDetailEntity findByOrderDetailId(int orderDetailId);

    public List<OrderDetailEntity> findByOrderId(int orderId);
    
}
