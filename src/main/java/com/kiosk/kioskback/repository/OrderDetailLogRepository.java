package com.kiosk.kioskback.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kiosk.kioskback.entity.OrderDetailLogEntity;
import com.kiosk.kioskback.entity.resultSet.GetAnalysisBusinessResultSet;
import com.kiosk.kioskback.entity.resultSet.ByCategoryResultSet;
import com.kiosk.kioskback.entity.resultSet.ByMenuResultSet;
import com.kiosk.kioskback.entity.resultSet.UserTop10ResultSet;

public interface OrderDetailLogRepository extends JpaRepository<OrderDetailLogEntity,Integer>{
  @Query(value = "SELECT category_id catedgoryId, category_name categoryName, count(category_id) saleCount, sum(price_with_option) totalPrice "
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
  @Query(value = "SELECT menu_id menuId, menu_name menuName, count(menu_name) saleCount, sum(price_with_option) totalPrice "
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
  @Query(value = "SELECT count(*) "
  + "FROM order_detail_log "
  + "WHERE store_id = :storeId "
  + "GROUP BY user_id, order_log_id " , nativeQuery = true
  )
  public int countTotalUserByStoreId(int storeId);
  
  @Query(value = "SELECT count(*) "
  + "FROM order_detail_log "
  + "WHERE store_id = :storeId AND user_id IN (SELECT user_id FROM user WHERE created_at BETWEEN :startedAt AND :endedAt) AND created_at BETWEEN :startedAt AND :endedAt "
  + "GROUP BY user_id, order_log_id " , nativeQuery = true
  )
  public int countNewUserByStoreId(int storeId, Date startedAt, Date endedAt);
  
  @Query(value = "SELECT a.user_id userId, a.user_name userName, a.tel_number telNumber, count(*) visitedCount, b.point point, sum(price_with_option) amountPayment"
  + "FROM order_detail_log a "
  + "LEFT JOIN point b "
  + "ON a.tel_number = b.tel_number "
  + "WHERE a.store_id = :storeId AND a.created_at BETWEEN :startedAt AND :endedAt "
  + "GROUP BY a.user_id, a.order_log_id "
  + "ORDER BY amountPayment DESC "
  , nativeQuery = true
  )
  public List<UserTop10ResultSet> findByTop10UserAndByStoreId(int storeId, Date startedAt, Date endedAt);


  @Query(value = "SELECT c.time time, count(c.orderId) saleCount, sum(c.saleAmount) saleAmount "
  + "FROM ( "
  + "SELECT a.order_log_id orderId, sum(a.price_with_option) saleAmount, DATE_FORMAT(b.created_at, '%H') time"
  + "FROM order_detail_log a "
  + "LEFT JOIN order_log b "
  + "ON a.order_log_id = b.order_log_id "
  + "WHERE a.store_id = :storeId AND a.created_at BETWEEN :startedAt AND :endedAt "
  + "GROUP BY a.order_log_id "
  + ") c "
  + "ORDER BY time ASC"
  , nativeQuery = true
  )
  public List<GetAnalysisBusinessResultSet> findByBusinessByTime(int storeId, Date startedAt, Date endedAt);
  
}


