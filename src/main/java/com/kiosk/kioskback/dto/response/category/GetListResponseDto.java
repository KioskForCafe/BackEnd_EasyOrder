package com.kiosk.kioskback.dto.response.category;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.CategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "전체 카테고리 리스트 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListResponseDto {

    @ApiModelProperty(value = "카테고리 번호", example = "1", required = true)
    private int menuCategoryId;

    @ApiModelProperty(value = "카테고리 이름", example = "Category Name", required = true)
    private String menuCategoryName;

    public GetListResponseDto(CategoryEntity categoryEntity) {
        this.menuCategoryId = categoryEntity.getMenuCategoryId();
        this.menuCategoryName = categoryEntity.getMenuCategoryName();
    }

    public static List<GetListResponseDto> copyList(List<CategoryEntity> categoryEntityList) {

        List<GetListResponseDto> list = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntityList) {
            GetListResponseDto dto = new GetListResponseDto(categoryEntity);
            list.add(dto);
        }

        return list;
    }
    
}
