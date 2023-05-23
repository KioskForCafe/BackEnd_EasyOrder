package com.kiosk.kioskback.service;

import com.kiosk.kioskback.dto.request.user.PatchUserDto;
import com.kiosk.kioskback.dto.request.user.PostCheckEmailDuplicateDto;
import com.kiosk.kioskback.dto.request.user.PostCheckIdDuplicateDto;
import com.kiosk.kioskback.dto.request.user.PostCheckTelNumberDuplicateDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.user.DeleteUserResponseDto;
import com.kiosk.kioskback.dto.response.user.GetUserResponseDto;
import com.kiosk.kioskback.dto.response.user.PatchUserResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckEmailDuplicateResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckIdDuplicateResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckTelNumberDuplicateResponseDto;

public interface UserServcie {

    //^ 이메일 중복체크
    public ResponseDto<PostCheckEmailDuplicateResponseDto> postCheckEmailDuplicate(PostCheckEmailDuplicateDto dto);
    
    //^ 아이디 중복체크
    public ResponseDto<PostCheckIdDuplicateResponseDto> postCheckIdDuplicate(PostCheckIdDuplicateDto dto);

    //^ 전화번호 중복체크
    public ResponseDto<PostCheckTelNumberDuplicateResponseDto> postCheckTelNumberDuplicate(PostCheckTelNumberDuplicateDto dto);
    
    //^ 회원 정보 조회
    public ResponseDto<GetUserResponseDto> getUser(String userId);
    
    //^ 회원 정보 수정
    public ResponseDto<PatchUserResponseDto> patchUser(String userId, PatchUserDto dto);

    //^ 회원 탈퇴
    public ResponseDto<DeleteUserResponseDto> deleteUser(String userId);
}
