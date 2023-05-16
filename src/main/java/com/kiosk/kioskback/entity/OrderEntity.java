package com.kiosk.kioskback.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "`Order`")
@Table(name = "`Order`")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private String userId;
    private int totalPrice;
    private Date updatedAt;
    private boolean orderState;
    private int storeId;

    public OrderEntity(int storeId, int totalPrice){
        Date now = new Date();
        this.totalPrice = totalPrice;
        this.updatedAt = now;
        this.orderState = true;
        this.storeId = storeId;
    }
    
}
