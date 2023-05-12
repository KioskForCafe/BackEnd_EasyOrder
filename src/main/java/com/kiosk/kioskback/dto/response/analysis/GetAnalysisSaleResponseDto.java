package com.kiosk.kioskback.dto.response.analysis;

import com.kiosk.kioskback.entity.resultSet.GetAnalysisSaleResultSet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "기간동안의 매장 매출 정보 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAnalysisSaleResponseDto {
    @ApiModelProperty()
    private int saleAmount;

    @ApiModelProperty()
    private int saleCount;

    @ApiModelProperty()
    private double avgSaleAmount;

    public GetAnalysisSaleResponseDto(GetAnalysisSaleResultSet getAnalysisSaleResultSet){
        this.saleAmount = getAnalysisSaleResultSet.getSaleAmount();
        this.saleCount = getAnalysisSaleResultSet.getSaleCount();
        this.avgSaleAmount = Math.round(((getAnalysisSaleResultSet.getSaleAmount() * 1.0) / getAnalysisSaleResultSet.getSaleCount()) * 100) / 100.0;
    }
    
}
