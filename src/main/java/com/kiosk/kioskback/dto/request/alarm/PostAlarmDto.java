package com.kiosk.kioskback.dto.request.alarm;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "알람 생성 Request Body")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostAlarmDto {
    private String message;
    private boolean isRead;
    private Date createdAt;    
    private int storeId;
}
