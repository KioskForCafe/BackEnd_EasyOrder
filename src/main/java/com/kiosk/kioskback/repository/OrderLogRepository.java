package com.kiosk.kioskback.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.OrderLogEntity;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLogEntity, Integer>{
  // @Query("SELECT SUM(s.revenue) FROM order_log s WHERE s.date BETWEEN DATE(:startDate) AND DATE(:endDate)")
  public List<OrderLogEntity> findTotalTotalPriceByCreatedAt(String startedAt, String endedAt);
}
