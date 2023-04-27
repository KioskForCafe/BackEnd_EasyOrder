package com.kiosk.kioskback.dto.response.analysis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상품별 매출 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ByMenuResponseDto {
    
    @ApiModelProperty(value = "상품명", example = "아메리카노", required = true)
    private String menuName;

    @ApiModelProperty(value = "판매 건수", example = "20",  required = true)
    private int saleCount;

    @ApiModelProperty(value = "판매 금액", example = "2000000", required = true)
    private int totalPrice;
}
