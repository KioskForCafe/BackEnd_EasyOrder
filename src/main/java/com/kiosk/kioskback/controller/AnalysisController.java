package com.kiosk.kioskback.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisBusinessResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisSaleResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisUserResponseDto;
import com.kiosk.kioskback.service.AnalysisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "매장 분석 모듈")
@RestController
@RequestMapping(ApiPattern.ANALYSIS)
public class AnalysisController {

    private static final String GET_SALE = "/sales/{storeId}/{startedAt}/{endedAt}";
    private static final String GET_BUSINESS = "/business/{storeId}/{startedAt}/{endedAt}";
    private static final String GET_MENU = "/menu/{storeId}/{startedAt}/{endedAt}";
    private static final String GET_USER = "/user/{storeId}/{startedAt}/{endedAt}";

    @Autowired private AnalysisService analysisService;

    @ApiOperation(value = "기간동안의 매장 매출 정보 조회", notes = "Request Header Athorization에 Bearer JWT를 포함하고 Path Variable에 매장 번호와 매출인식 시작일, 매출인식 마감일을 포함하여 요청하면, 성공 시 기간동안의 매출액, 판매 건수, 건당 평균 매출 금액 데이터를 반환, 실패 시 실패 메세지를 반환")
    @GetMapping(GET_SALE)
    public ResponseDto<GetAnalysisSaleResponseDto> getAnalysisSale(
        @AuthenticationPrincipal String userId,
        @ApiParam(value = "매장 번호", example = "1", required = true)
        @PathVariable("storeId") int storeId, 
        @ApiParam(value = "매출인식 시작일", example = "2023-04-27", required = true)
        @PathVariable("startedAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startedAt,
        @ApiParam(value = "매출인식 마감일", example = "2023-04-27", required = true)
        @PathVariable("endedAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endedAt
        ) {
            ResponseDto<GetAnalysisSaleResponseDto> response = analysisService.getAnalysisSale(userId, storeId, startedAt, endedAt);
            return response;
    }

    @ApiOperation(value = "매장 시간대별 매출 정보 조회", notes = "Request Header Athorization에 Bearer JWT를 포함하고 Path Variable에 매장 번호와 매출인식 시작일, 매출인식 마감일을 포함하여 요청하면, 성공 시 기간동안의 매출액의 시간대별 데이터(리스트)를 반환, 실패 시 실패 메세지를 반환")
    @GetMapping(GET_BUSINESS)
    public ResponseDto<List<GetAnalysisBusinessResponseDto>> getAnalysisBusiness(
        @AuthenticationPrincipal String userId,
        @ApiParam(value = "매장 번호", example = "1", required = true)
        @PathVariable("storeId") int storeId, 
        @ApiParam(value = "매출인식 시작일", example = "2023-04-27", required = true)
        @PathVariable("startedAt") String startedAt,
        @ApiParam(value = "매출인식 마감일", example = "2023-04-27", required = true)
        @PathVariable("endedAt") String endedAt
        ) {
        ResponseDto<List<GetAnalysisBusinessResponseDto>> response = analysisService.getAnalysisBusiness(userId, storeId, startedAt, endedAt);
        return response; 
    }

    @ApiOperation(value = "매장 상품별,카테고리별 매출 분석 정보 조회", notes = "Request Header Athorization에 Bearer JWT를 포함하고 Path Variable에 매장 번호와매출인식 시작일, 매출인식 마감일을 포함하여 요청하면, 성공 시 상품별, 카테고리별 판매 건수와 금액 데이터를 반환, 실패 시 실패 메세지를 반환")
    @GetMapping(GET_MENU)
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(
        @AuthenticationPrincipal String userId,
        @ApiParam(value = "매장 번호", example = "1", required = true)
        @PathVariable("storeId") int storeId, 
        @ApiParam(value = "매출인식 시작일", example = "2023-04-27", required = true)
        @PathVariable("startedAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startedAt,
        @ApiParam(value = "매출인식 마감일", example = "2023-04-27", required = true)
        @PathVariable("endedAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endedAt
        ) {
        ResponseDto<GetAnalysisMenuResponseDto> response = analysisService.getAnalysisMenu(userId, storeId, startedAt, endedAt);
        return response;
    }

    @ApiOperation(value = "매장별 회원 분석 정보 조회", notes = "Request Header Athorization에 Bearer JWT를 포함하고 Path Variable에 매장 번호와 매출인식 시작일, 매출인식 마감일을 포함하여 요청하면, 총 회원수, 신규 회원, 회원 평균 방문 수, Top 10 회원 정보(순위, 전화번호, 방문수, 적립 포인트, 결제 금액)의 데이터를 반환, 실패 시 실패 메세지를 반환")
    @GetMapping(GET_USER)
    public ResponseDto<GetAnalysisUserResponseDto> getAnalysisUser(
        @AuthenticationPrincipal String userId,
        @ApiParam(value = "매장 번호", example = "1", required = true)
        @PathVariable("storeId") int storeId, 
        @ApiParam(value = "매출인식 시작일", example = "2023-04-27", required = true)
        @PathVariable("startedAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startedAt,
        @ApiParam(value = "매출인식 마감일", example = "2023-04-27", required = true)
        @PathVariable("endedAt") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endedAt
    ) {
        ResponseDto<GetAnalysisUserResponseDto> response = analysisService.getAnalysisUser(userId, storeId, startedAt, endedAt);
        return response;
    }
}
