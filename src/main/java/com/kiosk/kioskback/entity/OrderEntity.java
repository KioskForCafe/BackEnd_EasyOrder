package com.kiosk.kioskback.entity;

import java.util.Date;

import com.kiosk.kioskback.dto.request.order.PatchOrderDto;

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
    private String orderState;
    private int storeId;

    public OrderEntity(int storeId, int totalPrice, String orderState){
        Date now = new Date();
        this.totalPrice = totalPrice;
        this.updatedAt = now;
        this.orderState = orderState;
        this.storeId = storeId;
    }

    public void patch(PatchOrderDto dto) {
        this.orderState = dto.getOrderState();
    }
}
