package com.kiosk.kioskback.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.OrderLogEntity;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLogEntity, Integer>{
  // orderLogId 리스트와 시작일, 마감일을 통해 orderLogEntity에서 totalPrice의 합을 가져옴
  @Query("SELECT SUM(ol.totalPrice) FROM OrderLogEntity ol WHERE ol.createdAt BETWEEN :startedAt AND :endedAt AND ol.orderLogId IN :orderLogIdList")
  public int findTotalTotalPriceByCreatedAt(@Param("startedAt") String startedAt, @Param("endedAt") String endedAt, List<Integer> orderLogIdList);

  @Query("SELECT ol FROM OrderLogEntity ol WHERE ol.orderLogId IN :orderLogIdList")
  public List<OrderLogEntity> findByOrderLogIdList(List<Integer> orderLogIdList);
}
