package com.kiosk.kioskback.dto.response.menu;

import java.util.List;

import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "메뉴 상세 정보 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuDetailResponseDto {
    @Schema(description = "카테고리 번호", required = true)
    private int categoryId;

    @Schema(description = "카테고리 번호", required = true)
    private String categoryName;

    @Schema(description = "메뉴 번호", required = true)
    private int menuId;

    @Schema(description = "메뉴 이름", required = true)
    private String menuName;

    @Schema(description = "메뉴 가격", required = true)
    private int menuPrice;

    @Schema(description = "메뉴 이미지", required = false)
    private String menuImgUrl;

    @Schema(description = "메뉴 상태(품절/판매)", required = true)
    private boolean menuState;

    @Schema(description = "매장 번호", required = true)
    private int storeId;

    @Schema(description = "옵션 리스트", required = false)
    private List<GetMenuDetailOptionResponseDto> optionList;

    public GetMenuDetailResponseDto(MenuEntity menuEntity, List<OptionEntity> optionList, String categoryName) {
        List<GetMenuDetailOptionResponseDto> GetMenuDetailOptionResponseDtoList = null;
        if(optionList != null){
            GetMenuDetailOptionResponseDtoList = GetMenuDetailOptionResponseDto.copyList(optionList);
        }
        this.categoryId = menuEntity.getCategoryId();
        this.categoryName = categoryName;
        this.menuId = menuEntity.getMenuId();
        this.menuName = menuEntity.getMenuName();
        this.menuPrice = menuEntity.getMenuPrice();
        this.menuImgUrl = menuEntity.getMenuImgUrl();
        this.menuState = menuEntity.isMenuState();
        this.storeId = menuEntity.getStoreId();
        this.optionList = GetMenuDetailOptionResponseDtoList;
    }
}
