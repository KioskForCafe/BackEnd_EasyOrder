package com.kiosk.kioskback.dto.response.point;

import com.kiosk.kioskback.entity.PointEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostPointResponseDto {
    private String telNumber;
    private int value;
    private boolean type;
    private int currentPoint;

    public PostPointResponseDto(PointEntity pointEntity){
        this.telNumber = pointEntity.getTelNumber();
        this.value = pointEntity.getValue();
        this.type = pointEntity.isType();
        this.currentPoint = pointEntity.getCurrentPoint();
    }
}
