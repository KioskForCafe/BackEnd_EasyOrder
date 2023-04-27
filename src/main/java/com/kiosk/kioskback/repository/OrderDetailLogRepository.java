package com.kiosk.kioskback.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiosk.kioskback.dto.response.analysis.ByCategoryResponseDto;
import com.kiosk.kioskback.entity.OrderDetailLogEntity;

public interface OrderDetailLogRepository extends JpaRepository<OrderDetailLogEntity,Integer>{
  @Query(value = "SELECT category_id, category_name, count(category_id) saleCount, sum(price_with_option) totalPrice "
  + "FROM order_detail_log "
  + "WHERE created_at between :startedAt and :endedAt "
  + "GROUP BY category_id "
  + "ORDER BY saleCount DESC , totalPrice DESC "
  )
  public List<ByCategoryResponseDto> findAllAnalysisByCategory(Date startedAt, Date endedAt);

  public List<OrderDetailLogEntity> findByStoreIdAndByCreatedAtBetween(int storeId, Date startedAt, Date endedAt);

  @Query(value = "SELECT menu_id, menu_name, count(menu_name) saleCount, sum(price_with_option) totalPrice "
  + "FROM order_detail_log "
  + "WHERE created_at between :startedAt and :endedAt "
  + "GROUP BY menu_id "
  + "ORDER BY saleCount DESC , totalPrice DESC "
  )
  public List<ByCategoryResponseDto> findAllAnalysisByMenu(Date startedAt, Date endedAt);
}


