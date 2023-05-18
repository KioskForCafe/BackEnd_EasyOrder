package com.kiosk.kioskback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.user.PatchUserDto;
import com.kiosk.kioskback.dto.request.user.PostCheckEmailDuplicateDto;
import com.kiosk.kioskback.dto.request.user.PostCheckIdDuplicateDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.user.DeleteUserResponseDto;
import com.kiosk.kioskback.dto.response.user.GetUserResponseDto;
import com.kiosk.kioskback.dto.response.user.PatchUserResponseDto;
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
    
    private final String GET_USER = "/";
    private final String PATCH_USER = "/";
    private final String DELETE = "/{userId}";
    private final String POST_CHECKEMAIL_DUPLICATE = "/duplicate/checkEmail";
    private final String POST_CHECKID_DUPLICATE = "/duplicate/checkId";

    @ApiOperation(value="회원 정보 조회", notes="Reqeust Header Authorization에 Bearer JWT를 포함하여 요청하면, 성공 시 회원 정보(아이디, 이름, 이메일, 전화번호)를 반환, 실패 시 실패 메세지를 반환")
    @GetMapping(GET_USER)
    public ResponseDto<GetUserResponseDto> getUser(@AuthenticationPrincipal String userId) {
        ResponseDto<GetUserResponseDto> response = userService.getUser(userId);
        return response;
    }

    @ApiOperation(value="회원 정보 수정", notes="Reqeust Header Authorization에 Bearer JWT를 포함하여 요청하면, 성공 시 회원 정보(아이디, 이름, 이메일, 전화번호)를 반환, 실패 시 실패 메세지를 반환")
    @PatchMapping(PATCH_USER)
    public ResponseDto<PatchUserResponseDto> patchUser(@AuthenticationPrincipal String userId, @RequestBody PatchUserDto requestBody) {
        ResponseDto<PatchUserResponseDto> response =userService.patchUser(userId, requestBody);
        return response;
    }

    @ApiOperation(
        value="회원 탈퇴", 
        notes="Request Header Athorization에 Bearer JWT를 포함하고 Path Variable에 Id를 포함하여 요청을 하면, 성공시 true를 반환, 실패시 실패 메세지를 반환")
    @DeleteMapping(DELETE)
    public ResponseDto<DeleteUserResponseDto> deleteUser(@AuthenticationPrincipal String userId){
        ResponseDto<DeleteUserResponseDto> response = userService.deleteUser(userId);
        return response;
    }

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
