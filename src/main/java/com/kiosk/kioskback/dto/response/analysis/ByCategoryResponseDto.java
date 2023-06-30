package com.kiosk.kioskback.dto.response.analysis;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.resultSet.ByCategoryResultSet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "카테고리 기준 상품 분석 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ByCategoryResponseDto {
    @Schema(description = "카테고리번호", example = "1", required = true)
    private int categoryId;
    @Schema(description = "카테고리명", example = "커피", required = true)
    private String categoryName;
    @Schema(description = "판매 건수", example = "10", required = true)
    private int saleCount;
    @Schema(description = "판매 금액", example = "30000", required = true)
    private int totalPrice;

    public ByCategoryResponseDto(ByCategoryResultSet byCategoryResultSet){
        this.categoryId = byCategoryResultSet.getCategoryId();
        this.categoryName = byCategoryResultSet.getCategoryName();
        this.saleCount = byCategoryResultSet.getSaleCount();
        this.totalPrice = byCategoryResultSet.getTotalPrice();
    }

    public static List<ByCategoryResponseDto> copy(List<ByCategoryResultSet> byCategoryResultSetList){
        List<ByCategoryResponseDto> list = new ArrayList<>();
        for(ByCategoryResultSet byCategoryResultSet : byCategoryResultSetList){
            ByCategoryResponseDto dto = new ByCategoryResponseDto(byCategoryResultSet);
            list.add(dto);
        }
        return list;
    }
}
