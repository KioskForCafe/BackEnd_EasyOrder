package com.kiosk.kioskback.dto.response.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "특정 메뉴 삭제 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMenuResponseDto {
    @Schema(description = "특정 메뉴 삭제 결과", example = "true", required = true)
    private boolean resultStatus;
}
