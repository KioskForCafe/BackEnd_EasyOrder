package com.kiosk.kioskback.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="사용자 전화번호 중복체크 Request Body")
@Data
@NoArgsConstructor
public class PostCheckTelNumberDuplicateDto {
    @ApiModelProperty(value="사용자 전화번호", example="010-1234-9876", required=true)
    @NotBlank
    private String telNumber;
}
