package com.kiosk.kioskback.service;

import java.util.List;

import com.kiosk.kioskback.dto.request.store.PatchStoreDto;
import com.kiosk.kioskback.dto.request.store.PostStoreDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.store.DeleteStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.GetStoreListResponseDto;
import com.kiosk.kioskback.dto.response.store.GetStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.PatchStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.PostStoreResponseDto;

public interface StoreService {
    
    public ResponseDto<GetStoreResponseDto> getStore(int storeId);

    //^ 매장 전체 조회
    public ResponseDto<List<GetStoreListResponseDto>> getStoreList(String userId);

    //^ 매장 등록
    public ResponseDto<PostStoreResponseDto> postStore(String userId, PostStoreDto requestbody);

    //^ 매장 정보 수정
    public ResponseDto<PatchStoreResponseDto> patchStore(String userId, PatchStoreDto requestbody);

    //^ 매장 삭제
    public ResponseDto<DeleteStoreResponseDto> deleteStore(String userId, int storeId);
}
