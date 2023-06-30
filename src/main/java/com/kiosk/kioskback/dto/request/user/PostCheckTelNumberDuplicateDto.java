package com.kiosk.kioskback.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="사용자 전화번호 중복체크 Request Body")
@Data
@NoArgsConstructor
public class PostCheckTelNumberDuplicateDto {
    @Schema(description="사용자 전화번호", example="010-1234-9876", required=true)
    @NotBlank
    private String telNumber;
}
