package com.kiosk.kioskback.dto.response.menu;

import java.util.List;

import com.kiosk.kioskback.dto.response.CategoryDto;
import com.kiosk.kioskback.dto.response.MenuDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "카테고리 안 메뉴 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuInCategoryResponseDto {
    @ApiModelProperty(value = "카테고리 정보", required = true)
    private CategoryDto category;

    @ApiModelProperty(value = "메뉴 정보 list", required = true)
    private List<MenuDto> menuList;
    
}
