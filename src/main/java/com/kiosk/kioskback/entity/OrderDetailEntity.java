package com.kiosk.kioskback.entity;

import com.kiosk.kioskback.dto.request.order.PostOrderDetailDto;

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

    public OrderDetailEntity(PostOrderDetailDto postOrderDetailDto) {
        this.menuId = postOrderDetailDto.getMenuId();
        this.menuCount = postOrderDetailDto.getMenuCount();
        this.orderId = postOrderDetailDto.getOrderId();
    }
    
}
