package com.kiosk.kioskback.dto.request.order;

import java.util.List;

import com.kiosk.kioskback.dto.request.OptionDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "장바구니 메뉴 추가 Request Body")
@Data
@NoArgsConstructor
public class PostOrderDetailDto {

    @ApiModelProperty(value = "상품 번호", example = "1", required = true)
    @NotEmpty
    private int menuId;

    @ApiModelProperty(value = "상품 갯수", example = "1", required = true)
    @NotEmpty
    private int menuCount;

    @ApiModelProperty(value = "주문 번호", example = "1", required = true)
    @NotEmpty
    private int orderId;

    @ApiModelProperty(value = "옵션 리스트", example = "1,2,3", required = false)
    private List<OptionDto> optionList;
    
}
