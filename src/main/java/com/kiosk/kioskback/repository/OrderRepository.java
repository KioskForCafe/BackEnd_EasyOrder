package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.OrderEntity;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{

    public List<OrderEntity> findByStoreIdAndOrderState(int storeId, String orderState);
    public OrderEntity findByOrderId(int orderId);
    
}
