package com.kiosk.kioskback.dto.response.category;

import com.kiosk.kioskback.entity.CategoryEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "카테고리 작성 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCategoryResponseDto {

    @ApiModelProperty(value = "카테고리 Entity", required = true)
    private CategoryEntity categoryEntity;
    
}
