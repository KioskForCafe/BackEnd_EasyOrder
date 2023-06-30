package com.kiosk.kioskback.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="유저 이메일 중복체크 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCheckEmailDuplicateResponseDto {
    @Schema(description="중복체크 결과", example="true", required=true)
    private boolean result;
}
