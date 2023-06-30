package com.kiosk.kioskback.dto.response.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kiosk.kioskback.entity.OrderEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "결제된 주문 리스트 조회 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponseDto {
    @Schema(description = "장바구니 번호", example = "1", required = true)
    private int orderId;
    @Schema(description = "총 가격", example = "18000", required = true)
    private int totalPrice;
    @Schema(description = "주문 상태", example = "접수", required = true)
    private String orderState;
    @Schema(description = "주문 시간", example = "2023-05-15 23:03:22", required = true)
    private Date updatedAt;
    
    public GetOrderResponseDto(OrderEntity orderEntity){
        this.orderId = orderEntity.getOrderId();
        this.totalPrice = orderEntity.getTotalPrice();
        this.orderState = orderEntity.getOrderState();
        this.updatedAt = orderEntity.getUpdatedAt();
    }

    public static List<GetOrderResponseDto> copyList(List<OrderEntity> orderEntityList){

        List<GetOrderResponseDto> list = new ArrayList<>();

        for(OrderEntity orderEntity : orderEntityList){
            GetOrderResponseDto getOrderResponseDto = new GetOrderResponseDto(orderEntity);
            list.add(getOrderResponseDto);
        }

        return list;

    }
    
}
