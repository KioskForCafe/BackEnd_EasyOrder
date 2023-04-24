package com.kiosk.kioskback.service;

import com.kiosk.kioskback.dto.request.auth.SignInDto;
import com.kiosk.kioskback.dto.request.auth.SignUpDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.auth.SignInResponseDto;
import com.kiosk.kioskback.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    public ResponseDto<SignUpResponseDto> signUp(SignUpDto dto);
    public ResponseDto<SignInResponseDto> signIn(SignInDto dto);
}
