package com.kiosk.kioskback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.point.GetPointDto;
import com.kiosk.kioskback.dto.request.point.PostPointDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.point.GetPointResponseDto;
import com.kiosk.kioskback.dto.response.point.PostPointResponseDto;
import com.kiosk.kioskback.service.PointService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// @Tag(description = "포인트 모듈")
@RestController
@RequestMapping(ApiPattern.POINT)
public class PointController {
    
    private static final String GET_POINT = "/{telNumber}";
    private static final String POST_POINT = "";

    @Autowired private PointService pointService;

    @Operation(summary = "포인트 조회", description = "")
    @GetMapping(GET_POINT)
    public ResponseDto<GetPointResponseDto> getPoint(@PathVariable("telNumber") String telNumber) {
        ResponseDto<GetPointResponseDto> response = pointService.getPoint(telNumber);
        return response;
    }
    @Operation(summary = "포인트 사용/적립", description = "")
    @PostMapping(POST_POINT)
    public ResponseDto<PostPointResponseDto> postPoint(@Valid @RequestBody PostPointDto requestBody) {
        ResponseDto<PostPointResponseDto> response = pointService.postPoint(requestBody);
        return response;
    }

}
