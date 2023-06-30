package com.kiosk.kioskback.dto.response.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="회원가입 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSignUpResponseDto {
    @Schema(description="회원가입 결과", example="true", required=true)
    private boolean status;
}
