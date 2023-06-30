package com.kiosk.kioskback.dto.request.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "주문 수정 - Request Body")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchOrderDto {

    @Schema(description = "주문번호", example = "1", required = true)
    @Min(1)
    private int orderId;

    @Schema(description = "주문 상태", example = "대기", required = true)
    @NotBlank
    private String orderState;
}
