package com.kiosk.kioskback.dto.response.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "특정 메뉴 삭제 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMenuResponseDto {
    @ApiModelProperty(value = "특정 메뉴 삭제 결과", example = "true", required = true)
    private boolean resultStatus;
}
