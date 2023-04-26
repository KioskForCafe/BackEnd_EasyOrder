package com.kiosk.kioskback.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;
import com.kiosk.kioskback.service.AnalysisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "분석 모듈")
@RestController
@RequestMapping(ApiPattern.ANALYSIS)
public class AnalysisController {

    @Autowired private AnalysisService analysisService;

    private final String GET_MENU = "/menu/{storeId}/{startedAt}/{endedAt}";
    
    @ApiOperation(
        value = "상품 분석 조회",
        notes = "Reqeust Header Authorization에 Bearer JWT를 포함하고 PathVariable에 storeId, startedAt, endedAt을 포함하여 요청을 하면, 성공시 카테고리별 분석정보, 상품별 분석정보를 최신순으로 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_MENU)
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(
        @AuthenticationPrincipal String userId,
        @PathVariable int storeId,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startedAt,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endedAt
    ){
        ResponseDto<GetAnalysisMenuResponseDto> response = analysisService.getAnalysisMenu(userId, storeId, startedAt, endedAt);
        return response;
    }
}
