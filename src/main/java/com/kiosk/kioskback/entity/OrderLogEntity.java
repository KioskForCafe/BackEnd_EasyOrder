package com.kiosk.kioskback.entity;

import java.util.Date;

import com.kiosk.kioskback.dto.request.order.PostOrderLogDto;

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
@Entity(name = "OrderLog")
@Table(name = "OrderLog")
public class OrderLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderLogId;
    private int orderId;
    private String userId;
    private String userName;
    private String telNumber;
    private int storeId;
    private String storeName;
    private Date createdAt;
    private int totalPrice;

    public OrderLogEntity(PostOrderLogDto dto) {
        this.orderId = dto.getOrderId();
        this.userId = dto.getUserId();
        this.userName = dto.getUserName();
        this.telNumber = dto.getTelNumber();
        this.storeId = dto.getStoreId();
        this.storeName = dto.getStoreName();
        this.createdAt = dto.getCreatedAt();
        this.totalPrice = dto.getTotalPrice();
    }
}
