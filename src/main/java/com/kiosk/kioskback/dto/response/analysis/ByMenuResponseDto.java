package com.kiosk.kioskback.dto.response.analysis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상품 기준 상품 분석 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ByMenuResponseDto {
    @ApiModelProperty(value = "상품번호", example = "1", required = true)
    private int menuId;
    @ApiModelProperty(value = "상품명", example = "시그니처", required = true)
    private String menuName;
    @ApiModelProperty(value = "판매 건수", example = "10", required = true)
    private int saleCount;
    @ApiModelProperty(value = "판매 금액", example = "30000", required = true)
    private int totalPrice;
}
