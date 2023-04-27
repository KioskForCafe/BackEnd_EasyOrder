package com.kiosk.kioskback.dto.response.analysis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "카테고리 기준 상품 분석 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ByCategoryResponseDto {
    @ApiModelProperty(value = "카테고리번호", example = "1", required = true)
    private int categoryId;
    @ApiModelProperty(value = "카테고리명", example = "커피", required = true)
    private String categoryName;
    @ApiModelProperty(value = "판매 건수", example = "10", required = true)
    private int saleCount;
    @ApiModelProperty(value = "판매 금액", example = "30000", required = true)
    private int totalPrice;
}
