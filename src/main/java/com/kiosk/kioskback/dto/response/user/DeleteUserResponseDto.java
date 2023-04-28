package com.kiosk.kioskback.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="회원 탈퇴 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserResponseDto {
    @ApiModelProperty(value = "회원 탈퇴 결과", example = "true", required = true)
    private boolean result;
}
