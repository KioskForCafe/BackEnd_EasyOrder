package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.dto.response.MenuResponseDto;
import com.kiosk.kioskback.dto.response.OptionResponseDto;
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
    private MenuResponseDto menuDto;

    public static List<OptionResponseDto> copyList(List<OptionEntity> optionEntityList) {

        List<OptionResponseDto> list = new ArrayList<>();
    
        for (OptionEntity optionEntity: optionEntityList) {
            OptionResponseDto dto = new OptionResponseDto(optionEntity);
            list.add(dto);
        }
    
        return list;
    
    }

}
