package com.kiosk.kioskback.dto.request.user;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="회원 정보 수정 Request Body")
@Data
@NoArgsConstructor
public class PatchUserDto {
    @Schema(description="사용자 아이디", example="comet7406", required=true)
    @NotBlank
    @Length(max=40)
    private String userId;

    @Schema(description="사용자 이름", example="홍길동", required=true)
    @NotBlank
    @Length(min=1, max=6)
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

}
