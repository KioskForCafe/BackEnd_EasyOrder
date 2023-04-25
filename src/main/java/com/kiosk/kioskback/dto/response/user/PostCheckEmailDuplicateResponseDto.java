package com.kiosk.kioskback.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="유저 이메일 중복체크 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCheckEmailDuplicateResponseDto {
    @ApiModelProperty(value="중복체크 결과", example="true", required=true)
    private boolean result;
}
