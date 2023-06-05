package com.kiosk.kioskback.entity;


import java.util.Date;

import com.kiosk.kioskback.dto.request.alarm.PostAlarmDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Alarm")
@Table(name="Alarm")
public class AlarmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alarmId;
    private String alarmMessage;
    private Date createdAt;
    private int storeId;
    private boolean isRead;

    public AlarmEntity(PostAlarmDto dto) {
        this.storeId = dto.getStoreId();
        this.alarmMessage = dto.getMessage();
        this.createdAt = dto.getCreatedAt();
        this.isRead = dto.isRead();
    }
}
