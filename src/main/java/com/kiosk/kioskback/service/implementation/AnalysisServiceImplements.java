package com.kiosk.kioskback.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.ByCategoryResponseDto;
import com.kiosk.kioskback.dto.response.analysis.ByMenuResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.OrderDetailLogRepository;
import com.kiosk.kioskback.repository.StoreRepository;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.AnalysisService;

@Service
public class AnalysisServiceImplements implements AnalysisService{

    @Autowired private UserRepository userRepository;
    @Autowired private StoreRepository storeRepository;
    @Autowired private OrderDetailLogRepository orderDetailLogRepository;

    //^ 상품 분석 조회
    @Override
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(String userId, int storeId, Date startedAt,
            Date endedAt) {
        
        GetAnalysisMenuResponseDto data = null;

        try {
            //? 로그인 유저 정보 불러오기
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            //? 로그인 유저가 관리자인지 검증
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);
            
            //? 매장 정보 가져오기
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE_ID);
            //? 로그인 유저의 매장이 맞는지 검증
            boolean isEqualUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            // List<ByCategoryResponseDto> analysisByCategoryList = orderDetailLogRepository.findAllAnalysisByCategory(storeId, startedAt, endedAt);
            // List<ByMenuResponseDto> analysisByMenuList = orderDetailLogRepository.findAllAnalysisByMenu(storeId, startedAt, endedAt);
            // data = new GetAnalysisMenuResponseDto(analysisByCategoryList, analysisByMenuList);
            


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}