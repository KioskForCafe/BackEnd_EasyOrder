package com.kiosk.kioskback.dto.response.user;

import com.kiosk.kioskback.entity.UserEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="회원 정보 수정 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserResponseDto {
    @Schema(description="사용자 아이디", example="comet7406", required=true)
    private String userId;

    @Schema(description="사용자 이름", example="홍길동", required=true)
    private String userName;

    @Schema(description="사용자 이메일", example="qwer@qwer.com", required=true)
    private String userEmail;

    @Schema(description="사용자 전화번호", example="010-1234-9876", required=true)
    private String telNumber;

    public PatchUserResponseDto(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.userName = userEntity.getUserName();
        this.userEmail = userEntity.getUserEmail();
        this.telNumber = userEntity.getTelNumber();
    }
}
