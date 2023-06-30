package com.kiosk.kioskback.dto.response.menu;

import com.kiosk.kioskback.dto.request.menu.PostMenuDto;
import com.kiosk.kioskback.entity.MenuEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "메뉴 추가하기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMenuResponseDto {
    @Schema(description = "상품 등록 결과 상태",example = "true", required = true)
    private boolean resultState;

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

}
