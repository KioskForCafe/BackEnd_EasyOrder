package com.kiosk.kioskback.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.sms.MessageDto;
import com.kiosk.kioskback.dto.request.sms.PostSmsCheckDto;
import com.kiosk.kioskback.dto.request.sms.SmsDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.sms.PostSmsCheckResponseDto;
import com.kiosk.kioskback.dto.response.sms.SmsResponseDto;
import com.kiosk.kioskback.service.SmsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@Api(description = "SMS 모듈")
@RestController
@RequestMapping(ApiPattern.SMS)
public class SmsController {

    @Autowired private SmsService smsService;

    private final String POST_SMS = "";
    private final String POST_SMS_CHECK = "/check";

    @ApiOperation(
        value = "SMS 인증코드 보내기",
        notes = "")
    @PostMapping(POST_SMS)
    public SmsResponseDto postSms(@RequestBody String telNumber) throws HttpMessageNotReadableException, InvalidKeyException, JsonProcessingException, RestClientException, NoSuchAlgorithmException, UnsupportedEncodingException, URISyntaxException{
        SmsResponseDto response = smsService.postSms(telNumber);
        return response;
    }

    @ApiOperation(
        value = "SMS 인증 코드 확인",
        notes = "")
    @PostMapping(POST_SMS_CHECK)
    public ResponseDto<PostSmsCheckResponseDto> postSmsCheck(@Valid @RequestBody PostSmsCheckDto requestBody){
        ResponseDto<PostSmsCheckResponseDto> response = smsService.postSmsCheck(requestBody);
        return response;
    }

}
