package com.kiosk.kioskback.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kiosk.kioskback.dto.request.sms.PostSmsCheckDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.sms.PostSmsCheckResponseDto;
import com.kiosk.kioskback.dto.response.sms.SmsResponseDto;

public interface SmsService {
    public String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException;
    public SmsResponseDto postSms(String telNumber) throws HttpMessageNotReadableException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, JsonProcessingException, RestClientException, URISyntaxException;
    public ResponseDto<PostSmsCheckResponseDto> postSmsCheck(PostSmsCheckDto requestBody);
}
