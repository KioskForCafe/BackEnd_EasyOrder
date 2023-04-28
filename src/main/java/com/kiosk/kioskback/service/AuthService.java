package com.kiosk.kioskback.service;

import com.kiosk.kioskback.dto.request.auth.PostSignInDto;
import com.kiosk.kioskback.dto.request.auth.PostSignUpDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.auth.PostSignInResponseDto;
import com.kiosk.kioskback.dto.response.auth.PostSignUpResponseDto;

public interface AuthService {

    //^ 회원가입
    public ResponseDto<PostSignUpResponseDto> postSignUp(PostSignUpDto dto);

    //^ 로그인
    public ResponseDto<PostSignInResponseDto> postSignIn(PostSignInDto dto);
}
