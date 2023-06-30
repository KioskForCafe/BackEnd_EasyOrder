package com.kiosk.kioskback.dto.response.category;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.CategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "전체 카테고리 리스트 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryResponseDto {

    @Schema(description = "카테고리 번호", example = "1", required = true)
    private int categoryId;

    @Schema(description = "카테고리 이름", example = "Category Name", required = true)
    private String categoryName;

    @Schema(description = "카테고리 순위", example = "1", required = true)
    private int categoryPriority;

    public GetCategoryResponseDto(CategoryEntity categoryEntity) {
        this.categoryId = categoryEntity.getCategoryId();
        this.categoryName = categoryEntity.getCategoryName();
        this.categoryPriority = categoryEntity.getCategoryPriority();
    }

    public static List<GetCategoryResponseDto> copyList(List<CategoryEntity> categoryEntityList) {

        List<GetCategoryResponseDto> list = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntityList) {
            GetCategoryResponseDto dto = new GetCategoryResponseDto(categoryEntity);
            list.add(dto);
        }

        return list;
    }
    
}
