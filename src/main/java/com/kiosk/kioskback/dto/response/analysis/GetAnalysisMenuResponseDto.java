package com.kiosk.kioskback.dto.response.analysis;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "상품 분석 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAnalysisMenuResponseDto {
    @Schema(description = "카테고리 기준 분석 리스트", required = true)
    private List<ByCategoryResponseDto> byCategory;
    @Schema(description = "상품 기준 분석 리스트", required = true)
    private List<ByMenuResponseDto> byMenu;
}
