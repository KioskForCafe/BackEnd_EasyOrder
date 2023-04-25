package com.kiosk.kioskback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.auth.PostSignInDto;
import com.kiosk.kioskback.dto.request.auth.PostSignUpDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.auth.PostSignInResponseDto;
import com.kiosk.kioskback.dto.response.auth.PostSignUpResponseDto;
import com.kiosk.kioskback.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiPattern.AUTH)
@Api(description = "인증 모듈")
public class AuthController {
    @Autowired private AuthService authService;

    private final String SIGN_UP = "/sign-up";
    private final String SIGN_IN = "/sign-in";

    @ApiOperation(value="회원가입", notes="아이디, 비밀번호, 이름, 이메일, 전화번호를입력하여 회원을 등록하고, 성공 시에는 회원가입 성공 여부에 true가 반환됨")
    @PostMapping(SIGN_UP)
    public ResponseDto<PostSignUpResponseDto> postSignUp(@Valid @RequestBody PostSignUpDto requestBody) {
        ResponseDto<PostSignUpResponseDto> response = authService.postSignUp(requestBody);
        return response;
    }

    @ApiOperation(value="로그인", notes="아이디와 비밀번호를 입력하면 일치할 경우, 회원 정보와 토큰 그리고 토큰 만료기간을 반환하고, 실패한다면 해당 메세지를 반환")
    @PostMapping(SIGN_IN)
    public ResponseDto<PostSignInResponseDto> postSignIn(@Valid @RequestBody PostSignInDto requestBody) {
        ResponseDto<PostSignInResponseDto> response = authService.postSignIn(requestBody);
        return response;
    }
}
