package com.kiosk.kioskback.dto.response.alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kiosk.kioskback.entity.AlarmEntity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "전체 알람 리스트 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAlarmResponseDto {
    private int alarmId;
    private String alarmMessage;
    private Date createdAt;
    private int storeId;
    private boolean isRead;

    public GetAlarmResponseDto(AlarmEntity alarmEntity) {
        this.alarmId = alarmEntity.getAlarmId();
        this.alarmMessage = alarmEntity.getAlarmMessage();
        this.createdAt = alarmEntity.getCreatedAt();
        this.storeId = alarmEntity.getStoreId();
        this.isRead = alarmEntity.isRead();
    }

    public static List<GetAlarmResponseDto> copyList(List<AlarmEntity> alarmEntityList) {

        List<GetAlarmResponseDto> list = new ArrayList<>();

        for (AlarmEntity alarmEntity : alarmEntityList) {
            GetAlarmResponseDto dto = new GetAlarmResponseDto(alarmEntity);
            list.add(dto);
        }

        return list;
    }
}
