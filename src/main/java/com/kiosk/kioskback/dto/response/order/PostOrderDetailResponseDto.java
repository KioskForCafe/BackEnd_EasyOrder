package com.kiosk.kioskback.dto.response.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "장바구니 메뉴 추가 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderDetailResponseDto {

    @ApiModelProperty(value = "장바구니 메뉴 추가 결과", example = "true", required = true)
    private boolean result;
    
}
