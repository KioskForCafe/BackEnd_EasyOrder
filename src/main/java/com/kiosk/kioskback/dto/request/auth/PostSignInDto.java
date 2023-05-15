package com.kiosk.kioskback.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="로그인 Request Body")
@Data
@NoArgsConstructor
public class PostSignInDto {
    @ApiModelProperty(value="사용자 아이디", example="comet7406", required=true)
    @NotBlank
    @Length(max=40)
    private String userId;

    @ApiModelProperty(value="사용자 비밀번호", example="P!ssw0rd", required=true)
    @NotBlank
    // @Length(min=8, max=20)
    private String password;
}
