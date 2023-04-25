package com.kiosk.kioskback.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.store.PatchStoreDto;
import com.kiosk.kioskback.dto.request.store.PostStoreDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.store.DeleteStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.GetStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.PatchStoreResponseDto;
import com.kiosk.kioskback.dto.response.store.PostStoreResponseDto;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.StoreRepository;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.StoreService;

public class StoreServiceImplements implements StoreService{

    @Autowired private UserRepository userRepository;
    @Autowired private StoreRepository storeRepository;

    //^ 매장 리스트 가져오기
    @Override
    public ResponseDto<List<GetStoreResponseDto>> getStore(String userId) {
        List<GetStoreResponseDto> data = null;

        try {
            //? 로그인 유저 정보 불러오기
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed("아이디가 존재 하지 않습니다.");
            //? 로그인 유저가 관리자인지 검증
            if(!userEntity.isAdmin()) return ResponseDto.setFailed("관리자 아이디가 아닙니다.");

            storeRepository.findByUserId



            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<PostStoreResponseDto> postStore(String userId, PostStoreDto requestbody) {

    }

    @Override
    public ResponseDto<PatchStoreResponseDto> patchStore(String userId, PatchStoreDto requestbody) {

    }

    @Override
    public ResponseDto<DeleteStoreResponseDto> deleteStore(String userId, int storeId) {

    }
    
}
