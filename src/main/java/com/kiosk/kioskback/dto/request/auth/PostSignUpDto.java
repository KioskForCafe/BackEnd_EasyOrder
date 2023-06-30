package com.kiosk.kioskback.dto.request.auth;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "회원가입 Request Body")
@Data
@NoArgsConstructor
public class PostSignUpDto {
    @Schema(description="사용자 아이디", example="comet7406", required=true)
    @NotBlank
    @Length(max=15)
    private String userId;

    @Schema(description="사용자 비밀번호", example="P!ssw0rd", required=true)
    @NotBlank
    @Length(min=8, max=20)
    private String password;

    @Schema(description="사용자 이름", example="홍길동", required=true)
    @NotBlank
    @Length(min=2, max=20)
    private String userName;

    @Schema(description="사용자 이메일", example="qwer@qwer.com", required=true)
    @NotBlank
    @Email
    @Length(max=45)
    private String userEmail;

    @Schema(description="사용자 전화번호", example="010-1234-9876", required=true)
    @NotBlank
    @Length(min=11, max=13)
    private String telNumber;

    @Schema(description="관리자 유무", example="true", required=true)
    private boolean admin;
}
