package com.kiosk.kioskback.dto.request;

import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "옵션 추가 Request Body")
@Data
@AllArgsConstructor
public class OptionDto {

    @Schema(description = "옵션 번호", required = true)
    @NotEmpty
    private int optionId;

    @Schema(description = "옵션 이름", required = true)
    @NotBlank
    private String optionName;

    @Schema(description = "옵션 가격", required = true)
    @NotEmpty
    private int optionPrice;

    public OptionDto(OptionEntity optionEntity) {
        this.optionId = optionEntity.getOptionId();
        this.optionName = optionEntity.getOptionName();
        this.optionPrice = optionEntity.getOptionPrice();
    }
    
}
