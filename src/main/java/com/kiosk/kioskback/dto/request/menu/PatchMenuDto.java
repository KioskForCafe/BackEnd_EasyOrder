package com.kiosk.kioskback.dto.request.menu;

import java.util.List;

import com.kiosk.kioskback.dto.response.MenuResponseDto;
import com.kiosk.kioskback.dto.response.OptionResponseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상품 수정 Request Body")
@Data
@NoArgsConstructor
public class PatchMenuDto {

    @ApiModelProperty(value = "메뉴 정보", required = true)
    private MenuResponseDto menuDto;

    @ApiModelProperty(value = "옵션 정보", required = true)
    private List<OptionResponseDto> optionList;

}
