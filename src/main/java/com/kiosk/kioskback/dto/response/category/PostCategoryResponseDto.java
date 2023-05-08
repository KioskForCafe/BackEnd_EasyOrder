package com.kiosk.kioskback.dto.response.category;

import java.util.ArrayList;
import java.util.List;

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

    @ApiModelProperty(value = "카테고리 번호", example = "1", required = true)
    private int categoryId;

    @ApiModelProperty(value = "카테고리 이름", example = "Category Name", required = true)
    private String categoryName;

    public PostCategoryResponseDto(CategoryEntity categoryEntity) {
        this.categoryId = categoryEntity.getCategoryId();
        this.categoryName = categoryEntity.getCategoryName();
    }
    
    public static List<PostCategoryResponseDto> copyList(List<CategoryEntity> categoryEntityList) {

        List<PostCategoryResponseDto> list = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntityList) {
            PostCategoryResponseDto dto = new PostCategoryResponseDto(categoryEntity);
            list.add(dto);
        }

        return list;
    }

}
