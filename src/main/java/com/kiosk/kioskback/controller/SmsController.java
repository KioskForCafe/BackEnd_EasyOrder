package com.kiosk.kioskback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.response.sms.SmsResponseDto;
import com.kiosk.kioskback.service.SmsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "SMS 모듈")
@RestController
@RequestMapping(ApiPattern.SMS)
public class SMSController {

    @Autowired private SmsService smsService;

    private final POST_SMS

    @ApiOperation(
        value = "SMS 보내기",
        notes = "Reqeust Header Authorization에 Bearer JWT를 포함하고 Request Body에 매장이름, 오픈시간, 마감시간, 로고 URL, 이미지 URL을 포함하여 요청을 하면, 성공시 매장 정보를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(POST)
    public SmsResponseDto postStore(@RequestBody PostStoreDto requestbody){
        ResponseDto<PostStoreResponseDto> response = storeService.postStore(userId, requestbody);
        return response;
    }

    
}
