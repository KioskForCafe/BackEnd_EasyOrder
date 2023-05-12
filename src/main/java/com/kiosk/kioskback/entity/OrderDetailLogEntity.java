package com.kiosk.kioskback.entity;

import java.util.Date;

import jakarta.persistence.Entity;
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
  private int orderDetailLogId;
  private int orderLogId;
  private String userId;
  private String telNumber;
  private int menuId;
  private String menuName;
  private int menuPrice;
  private int categoryId;
  private String categoryName;
  private int storeId;
  private String storeName;
  private String options;
  private int priceWithOption;
  private int count;
  private Date createdAt;
  
}
