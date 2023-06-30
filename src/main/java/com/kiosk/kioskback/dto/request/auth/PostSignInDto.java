package com.kiosk.kioskback.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="로그인 Request Body")
@Data
@NoArgsConstructor
public class PostSignInDto {
    @Schema(description="사용자 아이디", example="comet7406", required=true)
    @NotBlank
    @Length(max=40)
    private String userId;

    @Schema(description="사용자 비밀번호", example="P!ssw0rd", required=true)
    @NotBlank
    private String password;
}
