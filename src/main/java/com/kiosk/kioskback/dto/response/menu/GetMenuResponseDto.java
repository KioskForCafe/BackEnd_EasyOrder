package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.MenuEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "카테고리 안 메뉴 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuResponseDto {
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

    public GetMenuResponseDto(MenuEntity menuEntity) {
        this.menuId = menuEntity.getMenuId();
        this.menuName = menuEntity.getMenuName();
        this.menuPrice = menuEntity.getMenuPrice();
        this.menuImgUrl = menuEntity.getMenuImgUrl();
        this.menuState = menuEntity.isMenuState();
    }
    
    public static List<GetMenuResponseDto> copyList(List<MenuEntity> menuList){
        List<GetMenuResponseDto> list = new ArrayList<>();

        for (MenuEntity menuEntity: menuList) {
            GetMenuResponseDto dto = new GetMenuResponseDto(menuEntity);
            list.add(dto);
        }
    
        return list;

    }
}
