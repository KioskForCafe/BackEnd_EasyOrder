package com.kiosk.kioskback.service;

import com.kiosk.kioskback.dto.request.auth.PostSignInDto;
import com.kiosk.kioskback.dto.request.auth.PostSignUpDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.auth.PostSignInResponseDto;
import com.kiosk.kioskback.dto.response.auth.PostSignUpResponseDto;

public interface AuthService {
    public ResponseDto<PostSignUpResponseDto> postSignUp(PostSignUpDto dto);
    public ResponseDto<PostSignInResponseDto> postSignIn(PostSignInDto dto);
}
