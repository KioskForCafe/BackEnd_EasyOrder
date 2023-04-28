package com.kiosk.kioskback.dto.response.order;

import java.util.List;

import com.kiosk.kioskback.dto.response.OptionResponseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "장바구니 메뉴 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponseDto {

    @ApiModelProperty(value = "상세 주문 번호", example = "1", required = true)
    private int orderDetailId;

    @ApiModelProperty(value = "메뉴 이름", example = "아메리카노", required = true)
    private String menuName;

    @ApiModelProperty(value = "메뉴 가격", example = "2000", required = true)
    private int menuPrice;

    @ApiModelProperty(value = "옵션 리스트", example = "list", required = false)
    private List<OptionResponseDto> optionList;

    @ApiModelProperty(value = "상세 주문 수량", example = "3", required = true)
    private int orderDetailCount;

    
}
