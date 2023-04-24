package com.kiosk.kioskback.service;

import java.util.List;

import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.store.GetStoreResponseDto;

public interface StoreService {
    
    //^ 매장 전체 조회
    public ResponseDto<List<GetStoreResponseDto>> getStore(String userId);

}
