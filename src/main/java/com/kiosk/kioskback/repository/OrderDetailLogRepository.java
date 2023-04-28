package com.kiosk.kioskback.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kiosk.kioskback.entity.OrderDetailLogEntity;
import com.kiosk.kioskback.entity.resultSet.ByCategoryResultSet;
import com.kiosk.kioskback.entity.resultSet.ByMenuResultSet;

public interface OrderDetailLogRepository extends JpaRepository<OrderDetailLogEntity,Integer>{
  @Query(value = "SELECT category_id, category_name, count(category_id) saleCount, sum(price_with_option) totalPrice "
  + "FROM order_detail_log "
  + "WHERE store_id = :storeId AND created_at BETWEEN :startedAt AND :endedAt "
  + "GROUP BY category_id "
  + "ORDER BY saleCount DESC , totalPrice DESC ", nativeQuery = true
  )
  public List<ByCategoryResultSet> findAllAnalysisByCategory(int storeId, Date startedAt, Date endedAt);

  // @Query(value = "SELECT menu_id, menu_name, count(menu_name) saleCount, sum(price_with_option) totalPrice "
  // + "FROM order_detail_log "
  // + "WHERE created_at between :startedAt and :endedAt "
  // + "GROUP BY menu_id "
  // + "ORDER BY saleCount DESC , totalPrice DESC "
  // )
  // public List<ByMenuResponseDto> findAllAnalysisByMenu(int storeId, Date startedAt, Date endedAt);

  public List<OrderDetailLogEntity> findByStoreId(int storeId);
  @Query(value = "SELECT menu_id, menu_name, count(menu_name) saleCount, sum(price_with_option) totalPrice "
  + "FROM order_detail_log "
  + "WHERE store_id = :storeId AND created_at BETWEEN :startedAt AND :endedAt "
  + "GROUP BY menu_id "
  + "ORDER BY saleCount DESC , totalPrice DESC ", nativeQuery = true
  )
  public List<ByMenuResultSet> findAllAnalysisByMenu(int storeId, Date startedAt, Date endedAt);

  // @Query("SELECT HOUR(odl.createdAt) as hour, SUM(odl.totalPrice) as revenue, COUNT(odl.orderDetailLogId) as orderCount " 
  //        + "FROM OrderDetailLogEntity odl "
  //        + "WHERE odl.storeId = :storeId AND odl.createdAt BETWEEN :startedAt AND :endedAt " 
  //        + "GROUP BY HOUR(odl.createdAt) " 
  //        + "ORDER BY HOUR(odl.createdAt)")
         
//   @Query(value = "SELECT DATE_FORMAT(created_at, '%H') as hour, SUM(price_with_option) as totalSales " +
//       "FROM order_detail_log " +
//       "WHERE store_id = :storeId AND created_at BETWEEN :startTime AND :endTime " +
//       "GROUP BY hour ", nativeQuery = true
//       ) List<OrderDetailLogEntity> findHourlySalesByStoreIdAndCreatedAtBetween(@Param("storeId") int storeId, @Param("startedAt") LocalDateTime startedAt, @Param("endedAt") LocalDateTime endedAt);
}


