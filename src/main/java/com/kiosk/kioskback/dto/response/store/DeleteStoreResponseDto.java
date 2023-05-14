package com.kiosk.kioskback.dto.response.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "매장 삭제 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteStoreResponseDto {
    @ApiModelProperty(value = "매장 삭제 결과", example = "true", required = true)
    private boolean result;
}
