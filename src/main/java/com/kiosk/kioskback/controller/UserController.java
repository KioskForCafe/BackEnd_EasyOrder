package com.kiosk.kioskback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.user.PostCheckEmailDuplicateDto;
import com.kiosk.kioskback.dto.request.user.PostCheckIdDuplicateDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckEmailDuplicateResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckIdDuplicateResponseDto;
import com.kiosk.kioskback.service.UserServcie;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@Api(description="유저 모듈")
@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {

    @Autowired private UserServcie userService;
    
    private final String POST_CHECKEMAIL_DUPLICATE = "/checkEmail/duplicate";
    private final String POST_CHECKID_DUPLICATE = "/checkId/duplicate";

    @ApiOperation(value="유저 이메일 중복체크", notes="Request Body에 email을 포함하여 요청하면, 중복결과를 반환, 실패 시 실패 메세지를 반환")
    @PostMapping(POST_CHECKEMAIL_DUPLICATE)
    public ResponseDto<PostCheckEmailDuplicateResponseDto> postCheckEmailDuplicate(
        @Valid @RequestBody PostCheckEmailDuplicateDto requestBody
    ) {
        ResponseDto<PostCheckEmailDuplicateResponseDto> response = userService.postCheckEmailDuplicate(requestBody);
        return response;
    }

    @ApiOperation(value="유저 아이디 중복체크", notes="Request Body에 id를 포함하여 요청하면, 중복결과를 반환, 실패 시 실패 메세지를 반환")
    @PostMapping(POST_CHECKID_DUPLICATE)
    public ResponseDto<PostCheckIdDuplicateResponseDto> postCheckIdDuplicate(
        @Valid @RequestBody PostCheckIdDuplicateDto requestBody
    ) {
        ResponseDto<PostCheckIdDuplicateResponseDto> response = userService.postCheckIdDuplicate(requestBody);
        return response;
    }
}
