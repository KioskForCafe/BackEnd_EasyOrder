package com.kiosk.kioskback.dto.response.alarm;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="알람 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostAlarmResponseDto {
    private boolean alarmResult;
}
