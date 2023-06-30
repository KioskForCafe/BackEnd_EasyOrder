package com.kiosk.kioskback.dto.response.category;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.CategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "카테고리 작성 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCategoryResponseDto {
    @Schema(description = "카테고리 등록 결과 상태", example = "true", required = true)
    private boolean resultState;
}
