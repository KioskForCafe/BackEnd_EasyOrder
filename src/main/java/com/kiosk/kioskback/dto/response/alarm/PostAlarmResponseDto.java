package com.kiosk.kioskback.dto.response.alarm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="알람 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostAlarmResponseDto {
    private boolean alarmResult;
}
