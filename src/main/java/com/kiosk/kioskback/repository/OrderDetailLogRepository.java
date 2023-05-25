package com.kiosk.kioskback.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kiosk.kioskback.entity.OrderDetailLogEntity;
import com.kiosk.kioskback.entity.resultSet.GetAnalysisBusinessResultSet;
import com.kiosk.kioskback.entity.resultSet.GetAnalysisSaleResultSet;
import com.kiosk.kioskback.entity.resultSet.ByCategoryResultSet;
import com.kiosk.kioskback.entity.resultSet.ByMenuResultSet;
import com.kiosk.kioskback.entity.resultSet.UserTop10ResultSet;

public interface OrderDetailLogRepository extends JpaRepository<OrderDetailLogEntity,Integer>{
  @Query(value = "SELECT category_id categoryId, category_name categoryName, count(category_id) saleCount, sum(price_with_option) totalPrice "
  + "FROM order_detail_log "
  + "WHERE store_id = :storeId AND created_at BETWEEN :startedAt AND :endedAt + INTERVAL 1 DAY "
  + "GROUP BY category_id, category_name "
  + "ORDER BY saleCount DESC , totalPrice DESC ", nativeQuery = true
  )
  public List<ByCategoryResultSet> findAllAnalysisByCategory(@Param("storeId") int storeId,@Param("startedAt") Date startedAt,@Param("endedAt") Date endedAt);

  public List<OrderDetailLogEntity> findByStoreId(int storeId);
  @Query(value = "SELECT menu_id menuId, menu_name menuName, count(menu_name) saleCount, sum(price_with_option) totalPrice "
  + "FROM order_detail_log "
  + "WHERE store_id = :storeId AND created_at BETWEEN :startedAt AND :endedAt + INTERVAL 1 DAY "
  + "GROUP BY menu_id, menu_name "
  + "ORDER BY saleCount DESC , totalPrice DESC ", nativeQuery = true
  )
  public List<ByMenuResultSet> findAllAnalysisByMenu(@Param("storeId") int storeId,@Param("startedAt") Date startedAt,@Param("endedAt") Date endedAt);

  @Query(value = "SELECT count(distinct user_id) "
  + "FROM order_log "
  + "WHERE store_id = :storeId ", nativeQuery = true
  )
  public Integer countTotalUserByStoreId(@Param("storeId") int storeId);
  
  @Query(value = "SELECT count(distinct user_id) "
  + "FROM order_log "
  + "WHERE store_id = :storeId AND user_id IN (SELECT user_id FROM user WHERE created_at BETWEEN :startedAt AND :endedAt + INTERVAL 1 DAY) AND created_at BETWEEN :startedAt AND :endedAt + INTERVAL 1 DAY ", nativeQuery = true
  )
  public Integer countNewUserByStoreId(@Param("storeId") int storeId,@Param("startedAt") Date startedAt,@Param("endedAt") Date endedAt);
  
  @Query(value = "SELECT a.user_id userId, a.user_name userName, a.tel_number telNumber, count(*) visitedCount, b.point point, sum(a.total_price) amountPayment "
  + "FROM order_log a "
  + "LEFT JOIN point b "
  + "ON a.tel_number = b.tel_number "
  + "WHERE a.store_id = :storeId AND a.created_at BETWEEN :startedAt AND :endedAt + INTERVAL 1 DAY "
  + "GROUP BY a.user_id, a.order_log_id, a.user_name, a.tel_number "
  + "ORDER BY amountPayment DESC "
  , nativeQuery = true
  )
  public List<UserTop10ResultSet> findByTop10UserAndByStoreId(@Param("storeId") int storeId,@Param("startedAt") Date startedAt,@Param("endedAt") Date endedAt);


  @Query(value = "SELECT c.time time, count(c.orderId) saleCount, sum(c.saleAmount) saleAmount "
  + "FROM ( "
  + "SELECT a.order_log_id orderId, sum(a.price_with_option) saleAmount, DATE_FORMAT(b.created_at, '%H') time "
  + "FROM order_detail_log a "
  + "JOIN order_log b "
  + "ON a.order_log_id = b.order_log_id "
  + "WHERE a.store_id = :storeId AND a.created_at BETWEEN :startedAt AND :endedAt + INTERVAL 1 DAY "
  + "GROUP BY a.order_log_id "
  + ") c "
  + "GROUP BY time "
  + "ORDER BY time ASC "
  , nativeQuery = true
  )
  public List<GetAnalysisBusinessResultSet> findByBusinessByTime(@Param("storeId") int storeId,@Param("startedAt") Date startedAt,@Param("endedAt") Date endedAt);
  
  @Query(value = "SELECT IFNULL(count(*),0) saleCount, IFNULL(sum(c.saleAmount),0) saleAmount "
  + "FROM ( "
  + "SELECT a.order_log_id orderId, sum(a.price_with_option) saleAmount "
  + "FROM order_detail_log a "
  + "JOIN order_log b "
  + "ON a.order_log_id = b.order_log_id "
  + "WHERE a.store_id = :storeId AND a.created_at BETWEEN :startedAt AND :endedAt + INTERVAL 1 DAY "
  + "GROUP BY a.order_log_id "
  + ") c "
  , nativeQuery = true
  )
  public GetAnalysisSaleResultSet findBySale(@Param("storeId") int storeId,@Param("startedAt") Date startedAt,@Param("endedAt") Date endedAt);
}


