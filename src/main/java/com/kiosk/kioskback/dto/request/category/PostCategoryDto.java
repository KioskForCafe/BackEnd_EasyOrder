package com.kiosk.kioskback.dto.request.category;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "카테고리 작성 Request Body")
@Data
@NoArgsConstructor
public class PostCategoryDto {

    @ApiModelProperty(value = "카테고리 이름", example = "Category Name", required = true)
    @NotBlank
    @Length(max=45)
    private String categoryName;

    @ApiModelProperty(value = "카테고리 우선순위", example = "1", required = true)
    @Min(1)
    private int categoryPriority;

    @ApiModelProperty(value = "매장 식별 번호", example = "1", required = true)
    @Min(1)
    private int storeId;
    
}
