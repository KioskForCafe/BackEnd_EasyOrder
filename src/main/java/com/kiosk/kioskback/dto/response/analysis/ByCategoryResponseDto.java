package com.kiosk.kioskback.dto.response.analysis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "카테고리별 매출 Response Body - data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ByCategoryResponseDto {
    
    @ApiModelProperty(value = "카테고리명", example = "커피", required = true)
    private String categoryName;

    @ApiModelProperty(value = "판매 건수", example = "200", required = true)
    private int saleCount;

    @ApiModelProperty(value = "판매 금액", example = "200000", required = true)
    private int totalPrice;
}
