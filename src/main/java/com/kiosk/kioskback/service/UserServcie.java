package com.kiosk.kioskback.service;

import com.kiosk.kioskback.dto.request.user.PatchUserDto;
import com.kiosk.kioskback.dto.request.user.PostCheckEmailDuplicateDto;
import com.kiosk.kioskback.dto.request.user.PostCheckIdDuplicateDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.user.GetUserResponseDto;
import com.kiosk.kioskback.dto.response.user.PatchUserResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckEmailDuplicateResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckIdDuplicateResponseDto;

public interface UserServcie {
    public ResponseDto<PostCheckEmailDuplicateResponseDto> postCheckEmailDuplicate(PostCheckEmailDuplicateDto dto);
    public ResponseDto<PostCheckIdDuplicateResponseDto> postCheckIdDuplicate(PostCheckIdDuplicateDto dto);
    public ResponseDto<GetUserResponseDto> getUser(String userId);
    public ResponseDto<PatchUserResponseDto> patchUser(String userId, PatchUserDto dto);
}
