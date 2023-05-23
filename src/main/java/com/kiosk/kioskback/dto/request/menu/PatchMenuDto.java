package com.kiosk.kioskback.dto.request.menu;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상품 수정 Request Body")
@Data
@NoArgsConstructor
public class PatchMenuDto {
    @ApiModelProperty(value = "매장 번호", example = "1" ,required = true)
    @Min(1)
    private int storeId;

    @ApiModelProperty(value = "상품 번호", example = "1" ,required = true)
    @Min(1)
    private int menuId;

    @ApiModelProperty(value = "카테고리 번호", required = true)
    private Integer categoryId;

    @ApiModelProperty(value = "메뉴 이름", required = true)
    @NotBlank
    private String menuName;

    @ApiModelProperty(value = "메뉴 가격", required = true)
    @Min(0)
    private int menuPrice;

    @ApiModelProperty(value = "메뉴 상태(품절/판매)", required = true)
    @NotNull
    private Boolean menuState;

    @ApiModelProperty(value = "메뉴 이미지", required = false)
    private String menuImgUrl;

    @ApiModelProperty(value = "옵션 정보", required = true)
    private List<PatchMenuOptionDto> optionList;

}
