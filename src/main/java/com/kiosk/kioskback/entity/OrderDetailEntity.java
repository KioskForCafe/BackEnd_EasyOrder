package com.kiosk.kioskback.entity;

import com.kiosk.kioskback.dto.request.order.PostOrderDetailDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "OrderDetail")
@Table(name = "OrderDetail")
public class OrderDetailEntity {
    @Id
    private int orderDetailId;
    private int menuId;
    private int count;
    private int orderId;

    public OrderDetailEntity(PostOrderDetailDto postOrderDetailDto) {
        this.menuId = postOrderDetailDto.getMenuId();
        this.count = postOrderDetailDto.getMenuCount();
        this.orderId = postOrderDetailDto.getOrderId();
    }
    
}
