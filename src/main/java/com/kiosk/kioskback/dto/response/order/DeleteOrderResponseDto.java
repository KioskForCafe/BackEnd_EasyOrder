package com.kiosk.kioskback.dto.response.order;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "장바구니 메뉴 삭제 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderResponseDto {

    @ApiModelProperty(value = "주문 리스트", example = "list", required = false)
    private List<OrderDetailResponseDto> orderDetailList;

    @ApiModelProperty(value = "총 가격", example = "4000", required = true)
    private String totalPrice;
    
}
