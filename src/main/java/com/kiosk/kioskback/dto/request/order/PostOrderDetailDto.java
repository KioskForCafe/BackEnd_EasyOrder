package com.kiosk.kioskback.dto.request.order;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "상세 주문 리스트 Request Body")
@Data
@NoArgsConstructor
public class PostOrderDetailDto {

    @Schema(description = "상품 번호", example = "1", required = true)
    @Min(1)
    private int menuId;

    @Schema(description = "상품 갯수", example = "1", required = true)
    @Min(1)
    private int menuCount;
    
    @Schema(description = "옵션포함 가격", example = "1", required = true)
    @Min(1)
    private int priceWithOption;

    @Schema(description = "옵션 리스트", required = false)
    private List<Integer> optionList;
    
}
