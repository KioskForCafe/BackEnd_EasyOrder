package com.kiosk.kioskback.dto.response.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kiosk.kioskback.entity.OrderDetailEntity;
import com.kiosk.kioskback.entity.OrderDetailLogEntity;
import com.kiosk.kioskback.entity.OrderEntity;
import com.kiosk.kioskback.entity.OrderLogEntity;
import com.kiosk.kioskback.entity.resultSet.ByMenuResultSet;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "주문 내역 확인 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderListResponseDto {
    int orderId;
    String userId;
    Date createAt;
    int totalPrice;
    List<GetOrderDetailResponseDto> orderDetailList;

    public GetOrderListResponseDto (OrderEntity orderEntity, List<GetOrderDetailResponseDto> orderDetailList) {
        this.orderId = orderEntity.getOrderId();
        this.createAt = orderEntity.getUpdatedAt();
        this.userId = orderEntity.getUserId();
        this.totalPrice = orderEntity.getTotalPrice();
        this.orderDetailList = orderDetailList;

    }

}
