package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.dto.response.MenuDto;
import com.kiosk.kioskback.dto.response.OptionsDto;
import com.kiosk.kioskback.entity.OptionEntity;

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
    @ApiModelProperty(value = "메뉴 전체 정보", required = true)
    private MenuDto menuDto;

    @ApiModelProperty(value = "옵션 정보 list", required = true)
    private List<OptionsDto> optionsList;

    public static List<OptionsDto> copyList(List<OptionEntity> optionEntityList) {

        List<OptionsDto> list = new ArrayList<>();
    
        for (OptionEntity optionEntity: optionEntityList) {
            OptionsDto dto = new OptionsDto(optionEntity);
            list.add(dto);
        }
    
        return list;
    
    }
}
