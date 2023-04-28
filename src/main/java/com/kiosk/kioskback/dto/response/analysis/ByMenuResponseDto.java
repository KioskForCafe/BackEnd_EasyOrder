package com.kiosk.kioskback.dto.response.analysis;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.resultSet.ByMenuResultSet;

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

    public ByMenuResponseDto(ByMenuResultSet byMenuResultSet){
        this.menuId = byMenuResultSet.getMenuId();
        this.menuName = byMenuResultSet.getMenuName();
        this.saleCount = byMenuResultSet.getCount();
        this.totalPrice = byMenuResultSet.getSum();
    }

    public static List<ByMenuResponseDto> copy(List<ByMenuResultSet> byMenuResultSetList){
        List<ByMenuResponseDto> list = new ArrayList<>();
        for(ByMenuResultSet byMenuResultSet : byMenuResultSetList){
            ByMenuResponseDto dto = new ByMenuResponseDto(byMenuResultSet);
            list.add(dto);
        }
        return list;
    }
}
