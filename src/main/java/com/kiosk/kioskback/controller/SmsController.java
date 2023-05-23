package com.kiosk.kioskback.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.sms.MessageDto;
import com.kiosk.kioskback.dto.request.sms.SmsDto;
import com.kiosk.kioskback.dto.response.sms.SmsResponseDto;
import com.kiosk.kioskback.service.SmsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "SMS 모듈")
@RestController
@RequestMapping(ApiPattern.SMS)
public class SmsController {

    @Autowired private SmsService smsService;

    private final String POST_SMS = "";

    @ApiOperation(
        value = "SMS 보내기",
        notes = "Reqeust Header Authorization에 Bearer JWT를 포함하고 Request Body에 매장이름, 오픈시간, 마감시간, 로고 URL, 이미지 URL을 포함하여 요청을 하면, 성공시 매장 정보를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(POST_SMS)
    public SmsResponseDto postSMS(@RequestBody MessageDto requestbody) throws InvalidKeyException, JsonProcessingException, RestClientException, NoSuchAlgorithmException, UnsupportedEncodingException, URISyntaxException{
        SmsResponseDto response = smsService.sendSms(requestbody);
        return response;
    }

    
}
