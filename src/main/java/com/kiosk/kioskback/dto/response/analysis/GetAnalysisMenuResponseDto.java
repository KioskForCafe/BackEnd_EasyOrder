package com.kiosk.kioskback.dto.response.analysis;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상품 분석 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAnalysisMenuResponseDto {
    @ApiModelProperty(value = "카테고리 기준 분석 리스트", required = true)
    private List<ByCategoryResponseDto> byCategory;
    @ApiModelProperty(value = "상품 기준 분석 리스트", required = true)
    private List<ByMenuResponseDto> byMenu;
}
