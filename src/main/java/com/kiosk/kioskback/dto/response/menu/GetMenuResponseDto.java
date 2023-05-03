package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.dto.response.MenuResponseDto;
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

    @ApiModelProperty(value = "상품 정보", required = true)
    private MenuResponseDto menuDto;

    public GetMenuResponseDto(MenuEntity menuEntity) {
        this.menuDto = new MenuResponseDto(menuEntity, menuDto.getOptionList());
    }

    public static List<GetMenuResponseDto> copyList(List<MenuEntity> menuEntityList) {

        List<GetMenuResponseDto> list = new ArrayList<>();
    
        for (MenuEntity menuEntity: menuEntityList) {
            GetMenuResponseDto dto = new GetMenuResponseDto(menuEntity);
            list.add(dto);
        }
    
        return list;
    
    }
    
    
    
}
