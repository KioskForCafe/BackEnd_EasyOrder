package com.kiosk.kioskback.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.StoreRepository;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.AnalysisService;

@Service
public class AnalysisServiceImplements implements AnalysisService{

    @Autowired private UserRepository userRepository;
    @Autowired private StoreRepository storeRepository;

    //^ 상품 분석 조회
    @Override
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(String userId, int storeId, int startedAt,
            int endedAt) {
        
        GetAnalysisMenuResponseDto data = null;

        try {
            //? 로그인 유저 정보 불러오기
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            //? 로그인 유저가 관리자인지 검증
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);
            
            //? 매장 정보 가져오기
            // todo : 매장의 정보를 가져오는게 아니라 매장아이디에 해당하는 유저 아이디만 가져와도됨
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            //? 로그인 유저의 매장이 맞는지 검증
            boolean isEqualUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
