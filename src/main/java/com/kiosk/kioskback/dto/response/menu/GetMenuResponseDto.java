package com.kiosk.kioskback.dto.response.menu;

import java.util.List;

import com.kiosk.kioskback.dto.response.CategoryDto;
import com.kiosk.kioskback.dto.response.MenuDto;
import com.kiosk.kioskback.dto.response.OptionsDto;

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
    @ApiModelProperty(value = "메뉴 전체 정보", required = true)
    private MenuDto menuDto;

    @ApiModelProperty(value = "옵션 정보 list", required = true)
    private List<OptionsDto> optionsList;
    
}
