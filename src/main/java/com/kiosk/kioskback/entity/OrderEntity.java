package com.kiosk.kioskback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Order")
@Table(name = "Order")
public class OrderEntity {
    @Id
    private int orderId;
    private String userId;
    private int totalPrice;
    private String updatedAt;
    private boolean orderState;
    
}
