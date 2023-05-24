package com.kiosk.kioskback.dto.response.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kiosk.kioskback.entity.OrderEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchOrderResponseDto {
    @ApiModelProperty(value = "장바구니 번호", example = "1", required = true)
    private int orderId;
    @ApiModelProperty(value = "총 가격", example = "18000", required = true)
    private int totalPrice;
    @ApiModelProperty(value = "주문 상태", example = "접수", required = true)
    private String orderState;
    @ApiModelProperty(value = "주문 시간", example = "2023-05-15 23:03:22", required = true)
    private Date updatedAt;
    
    public PatchOrderResponseDto(OrderEntity orderEntity){
        this.orderId = orderEntity.getOrderId();
        this.totalPrice = orderEntity.getTotalPrice();
        this.orderState = orderEntity.getOrderState();
        this.updatedAt = orderEntity.getUpdatedAt();
    }

    public static List<PatchOrderResponseDto> copyList(List<OrderEntity> orderEntityList){

        List<PatchOrderResponseDto> list = new ArrayList<>();

        for(OrderEntity orderEntity : orderEntityList){
            PatchOrderResponseDto patchOrderResponseDto = new PatchOrderResponseDto(orderEntity);
            list.add(patchOrderResponseDto);
        }

        return list;

    }
}
