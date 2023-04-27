package com.kiosk.kioskback.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.OrderLogEntity;

public interface OrderLogRepository extends JpaRepository<OrderLogEntity, Integer>{
  
}
