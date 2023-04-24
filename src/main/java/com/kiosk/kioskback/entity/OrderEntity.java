package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    private int orderId;
    private String userId;
    private int totalPrice;
    private String updatedAt;
    private int orderState;
    
}
