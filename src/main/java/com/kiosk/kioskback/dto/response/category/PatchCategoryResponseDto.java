package com.kiosk.kioskback.dto.response.category;

import com.kiosk.kioskback.entity.CategoryEntity;
import com.kiosk.kioskback.entity.StoreEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "카테고리 수정 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchCategoryResponseDto {

    @ApiModelProperty(value = "매장 Entity", required = true)
    private StoreEntity storeEntity;

    @ApiModelProperty(value = "카테고리 Entity", required = true)
    private CategoryEntity categoryEntity;
    
}
