package com.kiosk.kioskback.service;

import com.kiosk.kioskback.dto.request.point.GetPointDto;
import com.kiosk.kioskback.dto.request.point.PostPointDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.point.GetPointResponseDto;
import com.kiosk.kioskback.dto.response.point.PostPointResponseDto;

public interface PointService {
    public ResponseDto<GetPointResponseDto> getPoint(String telNumber);
    public ResponseDto<PostPointResponseDto> postPoint(PostPointDto dto);
}
