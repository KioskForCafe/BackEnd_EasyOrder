package com.kiosk.kioskback.dto.request.order;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상세 주문 리스트 Request Body")
@Data
@NoArgsConstructor
public class PostOrderDetailDto {

    @ApiModelProperty(value = "상품 번호", example = "1", required = true)
    @Min(1)
    private int menuId;

    @ApiModelProperty(value = "상품 갯수", example = "1", required = true)
    @Min(1)
    private int menuCount;
    
    @ApiModelProperty(value = "옵션포함 가격", example = "1", required = true)
    @Min(1)
    private int priceWithOption;

    @ApiModelProperty(value = "옵션 리스트", required = false)
    private List<Integer> optionList;
    
}
