package com.kiosk.kioskback.dto.request.auth;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="회원가입 Request Body")
@Data
@NoArgsConstructor
public class PostSignUpDto {
    @ApiModelProperty(value="사용자 아이디", example="comet7406", required=true)
    @NotBlank
    @Length(max=15)
    private String userId;

    @ApiModelProperty(value="사용자 비밀번호", example="P!ssw0rd", required=true)
    @NotBlank
    @Length(min=8, max=20)
    private String password;

    @ApiModelProperty(value="사용자 이름", example="홍길동", required=true)
    @NotBlank
    @Length(min=2, max=6)
    private String userName;

    @ApiModelProperty(value="사용자 이메일", example="qwer@qwer.com", required=true)
    @NotBlank
    @Email
    @Length(max=45)
    private String userEmail;

    @ApiModelProperty(value="사용자 휴대전화번호", example="010-1234-9876", required=true)
    @NotBlank
    @Length(min=11, max=13)
    private String telNumber;

    @ApiModelProperty(value="관리자 유무", example="true", required=true)
    @NotBlank
    private boolean isAdmin;
}
