package com.kiosk.kioskback.dto.response.store;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "매장 등록 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostStoreResponseDto {
    @Schema(description = "매장 등록 결과", example = "true", required = true)
    private boolean result;
}
