package com.kiosk.kioskback.dto.response.menu;

import java.util.List;

import com.kiosk.kioskback.dto.response.OptionsDto;

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
    @ApiModelProperty(value = "상품 이름", required = true)
    private String menuName;

    @ApiModelProperty(value = "상품 가격", required = true)
    private int menuPrice;

    @ApiModelProperty(value = "옵션 정보 리스트", required = true)
    private List<OptionsDto> optionsList;
}
