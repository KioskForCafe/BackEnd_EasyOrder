package com.kiosk.kioskback.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kiosk.kioskback.dto.response.sms.SmsResponseDto;

@Service
public interface SmsService {
    public String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException;
    public SmsResponseDto sendSms(String telNumber) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, JsonProcessingException, RestClientException, URISyntaxException;
}
