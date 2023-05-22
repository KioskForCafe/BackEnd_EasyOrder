package com.kiosk.kioskback.dto.response.menu;

import java.util.List;

import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "메뉴 수정하기 Response Body - data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchMenuResponseDto {
    @ApiModelProperty(value = "카테고리 번호", required = true)
    private int categoryId;

    @ApiModelProperty(value = "카테고리 이름", required = true)
    private String categoryName;

    @ApiModelProperty(value = "메뉴 번호", required = true)
    private int menuId;

    @ApiModelProperty(value = "메뉴 이름", required = true)
    private String menuName;

    @ApiModelProperty(value = "메뉴 가격", required = true)
    private int menuPrice;

    @ApiModelProperty(value = "메뉴 이미지", required = false)
    private String menuImgUrl;

    @ApiModelProperty(value = "메뉴 상태(품절/판매)", required = true)
    private boolean menuState;

    @ApiModelProperty(value = "옵션 리스트", required = false)
    private List<PatchMenuOptionResponseDto> optionList;

    public PatchMenuResponseDto(MenuEntity menuEntity, List<OptionEntity> optionList, String categoryName) {
        this.menuId = menuEntity.getMenuId();
        this.categoryId = menuEntity.getCategoryId();
        this.categoryName = categoryName;
        this.menuName = menuEntity.getMenuName();
        this.menuPrice = menuEntity.getMenuPrice();
        this.menuImgUrl = menuEntity.getMenuImgUrl();
        this.menuState = menuEntity.isMenuState();
        this.optionList = PatchMenuOptionResponseDto.copyList(optionList);
    }
}
