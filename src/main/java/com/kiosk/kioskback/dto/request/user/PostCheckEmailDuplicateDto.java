package com.kiosk.kioskback.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="유저 이메일 중복체크 Request Body")
@Data
@NoArgsConstructor
public class PostCheckEmailDuplicateDto {
    @ApiModelProperty(value="유저 이메일", example="qwer@qwer.com", required=true)
    @NotBlank
    @Email
    private String userEmail;
}
