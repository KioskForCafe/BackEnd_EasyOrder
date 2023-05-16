package com.kiosk.kioskback.dto.request.order;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "주문 등록 Request Body")
@Data
@NoArgsConstructor
public class PostOrderDto {

    @ApiModelProperty(value = "매장 번호", example = "1", required = true)
    @Min(1)
    private int storeId;

    @ApiModelProperty(value = "총 가격", example = "1", required = true)
    @Min(0)
    private int totalPrice;

    @ApiModelProperty(value = "상세주문 리스트", example = "1", required = true)
    private List<PostOrderDetailDto> orderDetailList;
    
}
