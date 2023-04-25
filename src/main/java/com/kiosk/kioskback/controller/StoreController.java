package com.kiosk.kioskback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.store.PatchStoreDto;
import com.kiosk.kioskback.dto.request.store.PostStoreDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.store.GetStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.PatchStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.PostStoreResponseDto;
import com.kiosk.kioskback.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "매장 모듈")
@RestController
@RequestMapping(ApiPattern.STORE)
public class StoreController {
    @Autowired private StoreService storeService;

    private final String GET = "";
    private final String POST = "";
    private final String PATCH = "";

    @ApiOperation(
        value = "전체 매장 리스트 가져오기",
        notes = "Reqeust Header Authorization에 Bearer JWT를 포함하여 요청을 하면, 성공시 전체 매장 리스트를 최신순으로 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET)
    public ResponseDto<List<GetStoreResponseDto>> getStore(@AuthenticationPrincipal String userId){
        ResponseDto<List<GetStoreResponseDto>> response = storeService.getStore(userId);
        return response;
    }

    @ApiOperation(
        value = "매장 등록 하기",
        notes = "Reqeust Header Authorization에 Bearer JWT를 포함하고 Request Body에 매장이름, 오픈시간, 마감시간, 로고 URL, 이미지 URL을 포함하여 요청을 하면, 성공시 매장 정보를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(POST)
    public ResponseDto<PostStoreResponseDto> postStore(@AuthenticationPrincipal String userId, @RequestBody PostStoreDto requestbody){
        ResponseDto<PostStoreResponseDto> response = storeService.postStore(userId, requestbody);
        return response;
    }

    @ApiOperation(
        value = "매장 정보 수정 하기",
        notes = "Reqeust Header Authorization에 Bearer JWT를 포함하고 Request Body에 매장번호, 매장이름, 오픈시간, 마감시간, 로고 URL, 이미지 URL을 포함하여 요청을 하면, 성공시 매장 정보를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(PATCH)
    public ResponseDto<PatchStoreResponseDto> patchStore(@AuthenticationPrincipal String userId, @RequestBody PatchStoreDto requestbody){
        ResponseDto<PatchStoreResponseDto> response = storeService.patchStore(userId, requestbody);
        return response;
    }



    
}
