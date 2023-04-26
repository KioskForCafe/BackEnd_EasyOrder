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
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            //? 로그인 유저가 관리자인지 검증
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            List<StoreEntity> storeEntities = storeRepository.findByUserId(userId);
            data = GetStoreResponseDto.copyList(storeEntities);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //^ 매장 정보 등록
    @Override
    public ResponseDto<PostStoreResponseDto> postStore(String userId, PostStoreDto dto) {
        PostStoreResponseDto data = null;
        try {
            //? 로그인 유저 정보 불러오기
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            //? 로그인 유저가 관리자인지 검증
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            //? 매장 정보 입력하기
            StoreEntity storeEntity = new StoreEntity(dto, userId);
            storeRepository.save(storeEntity);

            //! 매장 정보 등록 후 정보를 반환해 주려고 했으나, 매장 번호가 sequence로 자동 저장되므로 번호를 줄 수 없다.
            //! 번호를 포함해서 주기위해서는 DB에서 조회가 필요하나 매장 데이터 중 unique한 컬럼이 없어 찾기가 애매하다.
            //! 매장등록 후 매장 정보를 줘야할 이유가 없다.
            // data = new PostStoreResponseDto(storeEntity);

            data = new PostStoreResponseDto(true);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //^ 매장 정보 수정
    @Override
    public ResponseDto<PatchStoreResponseDto> patchStore(String userId, PatchStoreDto dto) {
        PatchStoreResponseDto data = null;

        int storeId = dto.getStoreId();

        try {
            //? 로그인 유저 정보 불러오기
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            //? 로그인 유저가 관리자인지 검증
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            //? 매장 정보 가져오기
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);

            //? 로그인 유저의 매장이 맞는지 검증
            boolean isEqualUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            //? 매장 정보 수정하기
            storeEntity.patch(dto);
            //? 수정한 매장 정보 저장하기
            storeRepository.save(storeEntity);

            data = new PatchStoreResponseDto(storeEntity);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    //^ 매장 정보 삭제
    @Override
    public ResponseDto<DeleteStoreResponseDto> deleteStore(String userId, int storeId) {
        DeleteStoreResponseDto data = null;

        try {
            //? 로그인 유저 정보 불러오기
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            //? 로그인 유저가 관리자인지 검증
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            //? 매장 정보 가져오기
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);

            //? 로그인 유저의 매장이 맞는지 검증
            boolean isEqualUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            //? 매장 삭제 하기
            storeRepository.deleteById(storeEntity.getStoreId());

            data = new DeleteStoreResponseDto(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
