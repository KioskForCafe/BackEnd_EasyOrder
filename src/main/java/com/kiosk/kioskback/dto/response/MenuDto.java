package com.kiosk.kioskback.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    @ApiModelProperty(value = "메뉴 정보", required = true)
    private int menuId;

    @ApiModelProperty(value = "메뉴 이름", required = true)
    private String menuName;

    @ApiModelProperty(value = "메뉴 가격", required = true)
    private int menuPrice;

    @ApiModelProperty(value = "메뉴 이미지", required = true)
    private String menuImgUrl;

    @ApiModelProperty(value = "메뉴 상태(품절/판매)", required = true)
    private boolean menuState;
}
