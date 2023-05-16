package com.kiosk.kioskback.dto.response.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kiosk.kioskback.entity.OrderEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "결제된 주문 리스트 조회 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponseDto {
    @ApiModelProperty(value = "장바구니 번호", example = "1", required = true)
    private int orderId;
    @ApiModelProperty(value = "총 가격", example = "18000", required = true)
    private int totalPrice;
    @ApiModelProperty(value = "주문 시간", example = "2023-05-15 23:03:22", required = true)
    private Date updatedAt;
    
    public GetOrderResponseDto(OrderEntity orderEntity){
        this.orderId = orderEntity.getOrderId();
        this.totalPrice = orderEntity.getTotalPrice();
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
