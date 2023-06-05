package com.kiosk.kioskback.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.alarm.PostAlarmDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.alarm.GetAlarmResponseDto;
import com.kiosk.kioskback.dto.response.alarm.PostAlarmResponseDto;
import com.kiosk.kioskback.entity.AlarmEntity;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.AlarmRepository;
import com.kiosk.kioskback.repository.StoreRepository;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.AlarmService;

@Service
public class AlarmServiceImplements implements AlarmService {

    @Autowired UserRepository userRepository;
    @Autowired StoreRepository storeRepository;
    @Autowired AlarmRepository alarmRepository;
    
    @Override
    public ResponseDto<List<GetAlarmResponseDto>> getAlarm(String userId, int storeId) {
        List<GetAlarmResponseDto> data = null;

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);
            
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE_ID);

            boolean isEqualUserId = storeEntity.getUserId().equals(userId);
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            List<AlarmEntity> list = alarmRepository.findByStoreId(storeId);

            data = GetAlarmResponseDto.copyList(list);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    @Override
    public ResponseDto<PostAlarmResponseDto> postAlarm(String userId, PostAlarmDto dto) {
        PostAlarmResponseDto data = null;
        int storeId = dto.getStoreId();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE_ID);

            boolean isEqualUserId = storeEntity.getUserId().equals(userId);
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            AlarmEntity alarmEntity = new AlarmEntity(dto);
            alarmRepository.save(alarmEntity);

            data = new PostAlarmResponseDto(true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    
}
