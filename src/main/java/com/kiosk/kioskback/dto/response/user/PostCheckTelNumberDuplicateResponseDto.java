package com.kiosk.kioskback.dto.response.user;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCheckTelNumberDuplicateResponseDto {
    @Schema(description="중복체크 결과", example="true", required=true)
    private boolean result;
}
