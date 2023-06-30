package com.kiosk.kioskback.dto.response.order;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "장바구니 메뉴 삭제 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderResponseDto {

    @Schema(description = "주문 리스트", example = "list", required = false)
    private List<DeleteOrderDetailResponseDto> orderDetailList;

    @Schema(description = "총 가격", example = "4000", required = true)
    private String totalPrice;
    
}
