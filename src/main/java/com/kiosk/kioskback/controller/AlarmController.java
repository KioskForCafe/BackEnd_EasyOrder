package com.kiosk.kioskback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.alarm.PostAlarmDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.alarm.GetAlarmResponseDto;
import com.kiosk.kioskback.dto.response.alarm.PostAlarmResponseDto;
import com.kiosk.kioskback.service.AlarmService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Api(description = "매장 알람 모듈")
@RestController
@RequestMapping(ApiPattern.ALARM)
public class AlarmController {

    private static final String POST_ALARM = "";
    private static final String GET_ALARM_LIST = "/list/{storeId}";

    @Autowired private AlarmService alarmService;

    @ApiOperation(value = "알람 리스트 보기", notes = "Request Header Authorization에 Bearer JWT를 포함하고 Path Variable에 storeId를 포함하여 요청을 하면 성공 시 알람 전체 데이터를 반환, 실패 시 실패 메세지 반환")
    @GetMapping(GET_ALARM_LIST)
    public ResponseDto<List<GetAlarmResponseDto>> getAlarm(@AuthenticationPrincipal String userId, @PathVariable("storeId") int storeId) {
        ResponseDto<List<GetAlarmResponseDto>> response = alarmService.getAlarm(userId,storeId);
        return response;
    }

    @ApiOperation(value="등록 알람", notes="Request Header Athorization에 Bearer JWT를 포함하고 Request Body에 등록 정보를 포함하여 요청하면 성공시 알람 메세지를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(POST_ALARM)
    public ResponseDto<PostAlarmResponseDto> postAlarm(@AuthenticationPrincipal String userId, @Valid @RequestBody PostAlarmDto requestBody) {
        ResponseDto<PostAlarmResponseDto> response = alarmService.postAlarm(userId, requestBody);
        return response;
    }
    
}
