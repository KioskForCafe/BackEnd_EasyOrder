package com.kiosk.kioskback.dto.response.auth;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.kiosk.kioskback.entity.UserEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="로그인 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSignInResponseDto {
    @Schema(description="사용자 아이디", example="comet7406", required=true)
    private String userId;
    @Schema(description="사용자 이름", example="홍길동", required=true)
    private String userName;
    @Schema(description="사용자 휴대전화번호", example="010-1234-9876", required=true)
    private String TelNumber;
    @Schema(description="사용자 이메일", example="qwer@qwer.com", required=true)
    private String userEmail;
    @Schema(description="사용자 가입일", example="2023-04-24", required=true)
    private String createdAt;
    @Schema(description="관리자 유무", example="false", required=true)
    private boolean isAdmin;
    @Schema(description="JWT", example="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c", required=true)
    private String token;
    @Schema(description="토큰 만료 기간", example="3600000", required=true)
    private int expirtedTime;

    public PostSignInResponseDto(UserEntity userEntity, String token) {
        this.userId = userEntity.getUserId();
        this.userName = userEntity.getUserName();
        this.TelNumber = userEntity.getTelNumber();
        this.userEmail = userEntity.getUserEmail();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.createdAt = simpleDateFormat.format(userEntity.getCreatedAt());
        this.isAdmin = userEntity.isAdmin();
        this.token = token;
        this.expirtedTime = 3600000;
    }
}
