package com.kiosk.kioskback.dto.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchOrderDto {

    @ApiModelProperty(value = "주문번호", example = "1", required = true)
    private int orderId;

    @ApiModelProperty(value = "주문 상태", example = "대기", required = true)
    private String orderState;
}
