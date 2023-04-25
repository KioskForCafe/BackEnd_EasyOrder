package com.kiosk.kioskback.dto.response.category;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "카테고리 수정 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchCategoryResponseDto {

    @ApiModelProperty(value = "카테고리 식별 번호", required = true)
    private int categoryId;

    @ApiModelProperty(value = "카테고리 이름", required = true)
    private String categoryName;
    
}
