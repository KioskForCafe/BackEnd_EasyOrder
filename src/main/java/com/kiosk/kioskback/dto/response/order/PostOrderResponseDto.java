package com.kiosk.kioskback.dto.response.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "장바구니 메뉴 추가 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderResponseDto {

    @Schema(description = "장바구니 메뉴 추가 결과", example = "true", required = true)
    private boolean result;
    
}
