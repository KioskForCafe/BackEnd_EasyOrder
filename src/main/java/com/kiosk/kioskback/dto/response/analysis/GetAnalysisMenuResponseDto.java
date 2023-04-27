package com.kiosk.kioskback.dto.response.analysis;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "기간동안의 상품별 매장 매출 정보 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAnalysisMenuResponseDto {

    @ApiModelProperty(value = "카테고리별 매출 정보")
    private List<ByCategoryResponseDto> byCategoryResponseDtoList;

    @ApiModelProperty(value = "메뉴별 매출 정보")
    private List<ByMenuResponseDto> byMenuResponseDtoList;
    
}
