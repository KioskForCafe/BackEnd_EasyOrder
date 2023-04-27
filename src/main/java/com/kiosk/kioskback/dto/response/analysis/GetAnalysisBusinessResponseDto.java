package com.kiosk.kioskback.dto.response.analysis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "매장 시간대별 매출 정보 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAnalysisBusinessResponseDto {
    @ApiModelProperty()
    private int saleAmount;

    @ApiModelProperty()
    private int saleCount;

    @ApiModelProperty()
    private int time;
}
