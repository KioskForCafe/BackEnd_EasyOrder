package com.kiosk.kioskback.service.implementation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.ByCategoryResponseDto;
import com.kiosk.kioskback.dto.response.analysis.ByMenuResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisBusinessResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisSaleResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisUserResponseDto;
import com.kiosk.kioskback.entity.OrderDetailEntity;
import com.kiosk.kioskback.entity.OrderDetailLogEntity;
import com.kiosk.kioskback.dto.response.analysis.UserTop10ResponseDto;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.entity.resultSet.ByCategoryResultSet;
import com.kiosk.kioskback.entity.resultSet.ByMenuResultSet;
import com.kiosk.kioskback.entity.resultSet.GetAnalysisBusinessResultSet;
import com.kiosk.kioskback.entity.resultSet.UserTop10ResultSet;
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

            List<ByCategoryResultSet> byCategoryResultSets = orderDetailLogRepository.findAllAnalysisByCategory(storeId, startedAt, endedAt);
            List<ByCategoryResponseDto> analysisByCategoryList = ByCategoryResponseDto.copy(byCategoryResultSets);
            List<ByMenuResultSet> byMenuResultSets = orderDetailLogRepository.findAllAnalysisByMenu(storeId, startedAt, endedAt);
            List<ByMenuResponseDto> analysisByMenuList = ByMenuResponseDto.copy(byMenuResultSets);
            data = new GetAnalysisMenuResponseDto(analysisByCategoryList, analysisByMenuList);
            


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<GetAnalysisSaleResponseDto> getAnalysisSale(String userId, int storeId, String startedAt, String endedAt) {
        GetAnalysisSaleResponseDto data = null;

        try {
            // 유저 정보 불러오기
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);

            // 유저의 관리자 유무 검증
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            // 매장 정보 가져오기
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);

            // 유저의 매장이 맞는지 검증
            boolean isEqualUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            // 매장번호로 orderDetailLogEntity 리스트를 가져옴
            List<OrderDetailLogEntity> orderDetailLogEntityList = orderDetailLogRepository.findByStoreId(storeId);
            if(orderDetailLogEntityList == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER);

            int saleAmount = 0;
            int saleCount = 0;

            // orderDetailLogEntity 리스트에서 시작일과 마감일동안의 매출액, 판매 건수를 가져옴
            for(OrderDetailLogEntity orderDetailLogEntity: orderDetailLogEntityList) {
                String createdAt = orderDetailLogEntity.getCreatedAt();
                if(createdAt.compareTo(startedAt) >= 0 && createdAt.compareTo(endedAt) <= 0) {
                    int count = orderDetailLogEntity.getCount();
                    int priceWithOption = orderDetailLogEntity.getPriceWithOption();
                    int totalPrice = count * priceWithOption;
                    saleAmount += totalPrice;
                    saleCount += count;
                }
            }

            // 가져온 매출액과 판매 건수로 평균 매출액을 구함
            int avgSaleAmount = saleAmount/saleCount;

            data = new GetAnalysisSaleResponseDto(saleAmount, saleCount, avgSaleAmount);
            
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<GetAnalysisBusinessResponseDto>> getAnalysisBusiness(String userId, int storeId, String startedAt, String endedAt) {

        List<GetAnalysisBusinessResponseDto> data = null;

        try {
            // 유저 정보 불러오기
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);

            // 유저의 관리자 유무 검증
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            // 매장 정보 가져오기
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);

            // 유저의 매장이 맞는지 검증
            boolean isEqualUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            // 매장번호로 orderDetailLogEntity 리스트를 가져옴
            List<OrderDetailLogEntity> orderDetailLogEntityList = orderDetailLogRepository.findByStoreId(storeId);
            if(orderDetailLogEntityList == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER);

            // int saleAmount = 0;
            // int saleCount = 0;

            // // // String으로 받아온 startAt, endedAt을 LocalDateTime format으로 변환
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // LocalDateTime startedAtDateTime = LocalDateTime.parse(startedAt, formatter);
            // LocalDateTime endedAtDateTime = LocalDateTime.parse(endedAt, formatter);

            // // 각 시간대별 매출액, 판매 건수 저장을 위한 리스트 생성
            // List<Integer> saleAmountList = new ArrayList<>(23); // index가 시간이고 내용이 saleAmount인 리스트
            // List<Integer> saleCountList = new ArrayList<>(23); // index가 시간이고 내용이 saleCount인 리스트

            // // orderDetailLogEntity 리스트에서 시간대별로 saleAmount, saleCount를 가져옴
            // for(OrderDetailLogEntity orderDetailLogEntity :orderDetailLogEntityList) {
            //     LocalDateTime orderTime = LocalDateTime.parse(orderDetailLogEntity.getCreatedAt(), formatter);
            //     if(orderTime.isAfter(startedAtDateTime) && orderTime.isBefore(endedAtDateTime)) {
            //         int hourOfDay = orderTime.getHour();
            //         saleAmount = orderDetailLogEntity.getPriceWithOption();
            //         saleCount = orderDetailLogEntity.getCount();
            //         saleAmountList.set(hourOfDay,saleAmountList.get(hourOfDay) + saleAmount);
            //         saleCountList.set(hourOfDay, saleCountList.get(hourOfDay) + saleCount);
            //     }
            // }

            List<GetAnalysisBusinessResultSet> analysisBusinessResultSets = orderDetailLogRepository.findByBusinessByTime(storeId, startedAt, endedAt);

            GetAnalysisBusinessResponseDto dto = new GetAnalysisBusinessResponseDto();
            data = dto.copyList(analysisBusinessResultSets);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //^ 고객 분석 조회
    @Override
    public ResponseDto<GetAnalysisUserResponseDto> getAnalysisUser(String userId, int storeId, Date startedAt, Date endedAt) {
        
        GetAnalysisUserResponseDto data = null;

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

            int totalVisitedUserCount = orderDetailLogRepository.countTotalUserByStoreId(storeId);
            int newVisitedUserCount = orderDetailLogRepository.countNewUserByStoreId(storeId, startedAt, endedAt);
            List<UserTop10ResultSet> userTop10ResultSets = orderDetailLogRepository.findByTop10UserAndByStoreId(storeId, startedAt, endedAt);
            List<UserTop10ResponseDto> userTop10List = UserTop10ResponseDto.copy(userTop10ResultSets);
            
            data = new GetAnalysisUserResponseDto(totalVisitedUserCount, newVisitedUserCount, userTop10List);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }
    
}
