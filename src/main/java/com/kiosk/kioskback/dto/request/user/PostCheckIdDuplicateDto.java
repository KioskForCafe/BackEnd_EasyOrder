package com.kiosk.kioskback.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="사용자 아이디 중복체크 Request Body")
@Data
@NoArgsConstructor
public class PostCheckIdDuplicateDto {
    @Schema(description="사용자 아이디", example="qwerqwer", required=true)
    @NotBlank
    @Id
    private String userId;
}
