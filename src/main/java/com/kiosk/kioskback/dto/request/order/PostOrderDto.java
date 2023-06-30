package com.kiosk.kioskback.dto.request.order;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "주문 등록 Request Body")
@Data
@NoArgsConstructor
public class PostOrderDto {

    @Schema(description = "매장 번호", example = "1", required = true)
    @Min(1)
    private int storeId;

    @Schema(description = "총 가격", example = "1", required = true)
    @Min(0)
    private int totalPrice;

    @Schema(description = "상세주문 리스트", example = "1", required = true)
    private List<PostOrderDetailDto> orderDetailList;

    @Schema(description = "주문 상태", example = "대기", required = true)
    private String orderState;
    
}
