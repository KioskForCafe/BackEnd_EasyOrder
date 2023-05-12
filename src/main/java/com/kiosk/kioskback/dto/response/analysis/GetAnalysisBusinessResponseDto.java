package com.kiosk.kioskback.dto.response.analysis;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.resultSet.GetAnalysisBusinessResultSet;

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

    public GetAnalysisBusinessResponseDto(GetAnalysisBusinessResultSet analysisBusinessResultSet) {
        this.saleAmount = analysisBusinessResultSet.getSaleAmount();
        this.saleCount = analysisBusinessResultSet.getSaleCount();
        this.time = Integer.parseInt(analysisBusinessResultSet.getTime());
    }

    public List<GetAnalysisBusinessResponseDto> copyList(List<GetAnalysisBusinessResultSet> getAnalysisBusinessResultSets) {

        List<GetAnalysisBusinessResponseDto> list = new ArrayList<>();
    
        for (GetAnalysisBusinessResultSet getAnalysisBusinessResultSet: getAnalysisBusinessResultSets) {
            GetAnalysisBusinessResponseDto dto = new GetAnalysisBusinessResponseDto(getAnalysisBusinessResultSet);
            list.add(dto);
        }
    
        return list;
    
    }
}
