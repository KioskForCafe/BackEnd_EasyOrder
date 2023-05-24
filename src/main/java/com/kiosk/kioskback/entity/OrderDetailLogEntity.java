package com.kiosk.kioskback.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kiosk.kioskback.dto.request.order.PostOrderDetailLogDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "OrderDetailLog")
@Table(name = "OrderDetailLog")
public class OrderDetailLogEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int orderDetailLogId;
  private int orderLogId;
  private int menuId;
  private String menuName;
  private int menuPrice;
  private int categoryId;
  private String categoryName;
  private int storeId;
  private String storeName;
  private int priceWithOption;
  private int count;
  private Date createdAt;

  public OrderDetailLogEntity(PostOrderDetailLogDto dto, int orderLogId, Date orderLogDate) {
    this.orderLogId = orderLogId;
    this.menuId = dto.getMenuId();
    this.menuName = dto.getMenuName();
    this.menuPrice = dto.getMenuPrice();
    this.categoryId = dto.getCategoryId();
    this.categoryName = dto.getCategoryName();
    this.storeId = dto.getStoreId();
    this.storeName = dto.getStoreName();
    this.priceWithOption = dto.getPriceWithOption();
    this.count = dto.getCount();
    this.createdAt= orderLogDate;
  }
  
}
