package com.kiosk.kioskback.dto.request.user;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "유저 이메일 중복체크 Request Body")
@Data
@NoArgsConstructor
public class PostCheckEmailDuplicateDto {
    @Schema(description = "유저 이메일", example="qwer@qwer.com", required=true)
    @NotBlank
    @Email
    private String userEmail;
}
