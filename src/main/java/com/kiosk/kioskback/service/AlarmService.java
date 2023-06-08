package com.kiosk.kioskback.service;

import java.util.List;

import com.kiosk.kioskback.dto.request.alarm.PostAlarmDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.alarm.GetAlarmResponseDto;
import com.kiosk.kioskback.dto.response.alarm.PostAlarmResponseDto;

public interface AlarmService {

    public ResponseDto<List<GetAlarmResponseDto>> getAlarm(String userId, int storeId);
    public ResponseDto<PostAlarmResponseDto> postAlarm(String userId, PostAlarmDto dto);
    
}
