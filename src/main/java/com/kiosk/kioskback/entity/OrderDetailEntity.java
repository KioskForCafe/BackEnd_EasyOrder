package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEntity {

    private int orderDetailId;
    private int menuId;
    private int menuCount;
    private int orderId;
    
}
