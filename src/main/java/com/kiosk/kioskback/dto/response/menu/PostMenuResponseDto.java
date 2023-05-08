package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.dto.request.menu.PostMenuDto;
import com.kiosk.kioskback.entity.MenuEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "메뉴 추가하기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMenuResponseDto {
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

    public PostMenuResponseDto(MenuEntity menuEntity) {
        this.menuId = menuEntity.getMenuId();
        this.menuName = menuEntity.getMenuName();
        this.menuPrice = menuEntity.getMenuPrice();
        this.menuImgUrl = menuEntity.getMenuImgUrl();
        this.menuState = menuEntity.isMenuState();
    }

    public static MenuEntity toMenuEntity (PostMenuDto dto){
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setMenuImgUrl(dto.getMenuImgUrl());
        menuEntity.setMenuName(dto.getMenuName());
        menuEntity.setMenuPrice(dto.getMenuPrice());
        menuEntity.setMenuState(dto.getMenuState());
        menuEntity.setCategoryId(dto.getCategoryId());
        menuEntity.setStoreId(dto.getStoreId());

        return menuEntity;
    }

    public static List<PostMenuResponseDto> copyList(List<MenuEntity> menuList){
        List<PostMenuResponseDto> list = new ArrayList<>();

        for (MenuEntity menuEntity: menuList) {
            PostMenuResponseDto dto = new PostMenuResponseDto(menuEntity);
            list.add(dto);
        }
    
        return list;

    }


}
