package com.kiosk.kioskback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.store.PatchStoreDto;
import com.kiosk.kioskback.dto.request.store.PostStoreDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.store.DeleteStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.GetStoreListResponseDto;
import com.kiosk.kioskback.dto.response.store.GetStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.PatchStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.PostStoreResponseDto;
import com.kiosk.kioskback.service.StoreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


// @Tag(description = "매장 모듈")
@RestController
@RequestMapping(ApiPattern.STORE)
public class StoreController {
    @Autowired private StoreService storeService;

    private final String GET = "/{storeId}";
    private final String GET_LIST = "";
    private final String POST = "";
    private final String PATCH = "";
    private final String DELETE = "/{storeId}";

    @Operation(
        summary = "매장 정보 가져오기",
        description = "PathVariable에 StoreId를 포함하여 요청을 하면, 성공시 매장 정보를 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET)
    public ResponseDto<GetStoreResponseDto> getStore(@PathVariable("storeId") int storeId){
        ResponseDto<GetStoreResponseDto> response = storeService.getStore(storeId);
        return response;
    }

    @Operation(
        summary = "전체 매장 리스트 가져오기",
        description = "Reqeust Header Authorization에 Bearer JWT를 포함하여 요청을 하면, 성공시 전체 매장 리스트를 최신순으로 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_LIST)
    public ResponseDto<List<GetStoreListResponseDto>> getStoreList(@AuthenticationPrincipal String userId){
        ResponseDto<List<GetStoreListResponseDto>> response = storeService.getStoreList(userId);
        return response;
    }

    @Operation(
        summary = "매장 등록 하기",
        description = "Reqeust Header Authorization에 Bearer JWT를 포함하고 Request Body에 매장이름, 오픈시간, 마감시간, 로고 URL, 이미지 URL을 포함하여 요청을 하면, 성공시 매장 정보를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(POST)
    public ResponseDto<PostStoreResponseDto> postStore(@AuthenticationPrincipal String userId, @RequestBody PostStoreDto requestbody){
        ResponseDto<PostStoreResponseDto> response = storeService.postStore(userId, requestbody);
        return response;
    }

    @Operation(
        summary = "매장 정보 수정 하기",
        description = "Reqeust Header Authorization에 Bearer JWT를 포함하고 Request Body에 매장번호, 매장이름, 오픈시간, 마감시간, 로고 URL, 이미지 URL을 포함하여 요청을 하면, 성공시 매장 정보를 반환, 실패시 실패 메세지를 반환")
    @PatchMapping(PATCH)
    public ResponseDto<PatchStoreResponseDto> patchStore(@AuthenticationPrincipal String userId, @RequestBody PatchStoreDto requestbody){
        ResponseDto<PatchStoreResponseDto> response = storeService.patchStore(userId, requestbody);
        return response;
    }
    
    @Operation(
        summary = "매장 삭제 하기",
        description = "Reqeust Header Authorization에 Bearer JWT를 포함하고 PathVariable에 매장번호를 포함하여 요청을 하면, 성공시 매장 삭제 결과를 반환, 실패시 실패 메세지를 반환")
    @DeleteMapping(DELETE)
    public ResponseDto<DeleteStoreResponseDto> deleteStore(@AuthenticationPrincipal String userId, @PathVariable int storeId){
        ResponseDto<DeleteStoreResponseDto> response = storeService.deleteStore(userId, storeId);
        return response;
    }


    
}
