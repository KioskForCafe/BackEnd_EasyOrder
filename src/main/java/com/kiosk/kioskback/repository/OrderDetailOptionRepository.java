package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.OrderDetailOptionEntity;
import com.kiosk.kioskback.entity.primaryKey.OrderDetailOptionPK;

public interface OrderDetailOptionRepository extends JpaRepository<OrderDetailOptionEntity,OrderDetailOptionPK>{
    public List<OrderDetailOptionEntity> findByOrderDetailId(int orderDetailId);
}
