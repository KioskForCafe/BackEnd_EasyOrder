package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.dto.response.MenuDto;
import com.kiosk.kioskback.dto.response.OptionDto;
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
    @ApiModelProperty(value = "메뉴 전체 정보", required = true)
    private MenuDto menuDto;

    public static List<OptionDto> copyList(List<OptionEntity> optionEntityList) {

        List<OptionDto> list = new ArrayList<>();
    
        for (OptionEntity optionEntity: optionEntityList) {
            OptionDto dto = new OptionDto(optionEntity);
            list.add(dto);
        }
    
        return list;
    
    }

}
