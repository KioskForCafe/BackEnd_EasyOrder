package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.OrderLogEntity;

@Repository
public interface OrderLogRepository  extends JpaRepository<OrderLogEntity, Integer>{

    
    
}
