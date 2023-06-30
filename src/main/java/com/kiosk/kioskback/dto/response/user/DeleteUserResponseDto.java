package com.kiosk.kioskback.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="회원 탈퇴 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserResponseDto {
    @Schema(description = "회원 탈퇴 결과", example = "true", required = true)
    private boolean result;
}
