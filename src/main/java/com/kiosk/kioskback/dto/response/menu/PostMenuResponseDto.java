package com.kiosk.kioskback.dto.response.menu;

import java.util.List;

import com.kiosk.kioskback.dto.response.MenuDto;
import com.kiosk.kioskback.dto.response.OptionsDto;

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
    @ApiModelProperty(value = "카테고리 번호", required = true)
    private int categoryId;

    @ApiModelProperty(value = "메뉴 정보 리스트", required = true)
    private List<MenuDto> menuList;

    @ApiModelProperty(value = "옵션 정보 리스트", required = true)
    private List<OptionsDto> optionsList;
}
