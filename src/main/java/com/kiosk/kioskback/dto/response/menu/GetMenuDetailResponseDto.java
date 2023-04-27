package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.dto.response.MenuDto;
import com.kiosk.kioskback.dto.response.OptionDto;
import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "메뉴 상세 정보 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuDetailResponseDto {
    @ApiModelProperty(value = "상품 정보", required = true)
    private MenuDto menuDto;

    @ApiModelProperty(value = "옵션 정보 리스트", required = true)
    private List<OptionDto> optionsList;

    public GetMenuDetailResponseDto(MenuEntity menuEntity, List<OptionEntity> optionList) {
        this.menuDto = new MenuDto(menuEntity);
        this.optionsList = copyList(optionList);
    }
    
    public static List<OptionDto> copyList(List<OptionEntity> optionEntityList) {

        List<OptionDto> list = new ArrayList<>();
    
        for (OptionEntity optionEntity: optionEntityList) {
            OptionDto dto = new OptionDto(optionEntity);
            list.add(dto);
        }
    
        return list;
    
    }
}
