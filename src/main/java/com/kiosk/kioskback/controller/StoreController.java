package com.kiosk.kioskback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.store.GetStoreResponseDto;
import com.kiosk.kioskback.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "매장 모듈")
@RestController
@RequestMapping(ApiPattern.STORE)
public class StoreController {
    @Autowired private StoreService storeService;

    private final String GET = "";

    //^ 매장 전체 조회
    @ApiOperation(
        value = "전체 매장 리스트 가져오기",
        notes = "Reqeust Header Authorization에 Bearer JWT를 포함하여 요청을 하면, 성공시 전체 매장 리스트를 최신순으로 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET)
    public ResponseDto<List<GetStoreResponseDto>> getStore(@AuthenticationPrincipal String userId){
        ResponseDto<List<GetStoreResponseDto>> response = storeService.getStore(userId);
        return response;
    }

    
}
