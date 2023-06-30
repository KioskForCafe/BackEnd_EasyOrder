package com.kiosk.kioskback.dto.request.category;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "카테고리 작성 Request Body")
@Data
@NoArgsConstructor
public class PostCategoryDto {

    @Schema(description = "카테고리 이름", example = "Category Name", required = true)
    @NotBlank
    @Length(max=45)
    private String categoryName;

    @Schema(description = "카테고리 우선순위", example = "1", required = true)
    @Min(1)
    private int categoryPriority;

    @Schema(description = "매장 식별 번호", example = "1", required = true)
    @Min(1)
    private int storeId;
    
}
