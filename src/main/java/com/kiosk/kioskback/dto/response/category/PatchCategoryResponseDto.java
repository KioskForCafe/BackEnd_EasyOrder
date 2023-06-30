package com.kiosk.kioskback.dto.response.category;


import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.CategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "카테고리 수정 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchCategoryResponseDto {

    @Schema(description = "카테고리 식별 번호", required = true)
    private int categoryId;

    @Schema(description = "카테고리 이름", required = true)
    private String categoryName;

    @Schema(description = "카테고리 우선순위", required = true)
    private int categoryPriority;

    public PatchCategoryResponseDto(CategoryEntity categoryEntity){
        this.categoryId = categoryEntity.getCategoryId();
        this.categoryName = categoryEntity.getCategoryName();
        this.categoryPriority = categoryEntity.getCategoryPriority();
    }

    public static List<PatchCategoryResponseDto> copyList(List<CategoryEntity> categoryEntities){
        List<PatchCategoryResponseDto> list = new ArrayList<>();
        for(CategoryEntity categoryEntity : categoryEntities){
            PatchCategoryResponseDto patchCategoryResponseDto = new PatchCategoryResponseDto(categoryEntity);
            list.add(patchCategoryResponseDto);
        }
        return list;
    }
    
}
