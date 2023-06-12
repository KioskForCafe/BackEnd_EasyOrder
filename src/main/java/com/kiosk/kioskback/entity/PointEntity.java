package com.kiosk.kioskback.entity;

import com.kiosk.kioskback.dto.request.point.PostPointDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Point")
@Table(name = "Point")
public class PointEntity {

    @Id
    private int pointId;
    private String telNumber;
    private int value;
    private Boolean type;
    private int currentPoint;

    public PointEntity(PostPointDto dto){
        this.telNumber = dto.getTelNumber();
        this.value = dto.getValue();
        this.type = dto.getType();
        this.currentPoint = dto.getCurrentPoint();
    }
    
    public PointEntity(int currentPoint, PostPointDto dto){
        this.telNumber = dto.getTelNumber();
        this.value = dto.getValue();
        boolean isType = dto.getType();
        this.type = isType;
        if(isType){
            this.currentPoint = currentPoint + dto.getCurrentPoint();
        }else{
            this.currentPoint = currentPoint - dto.getCurrentPoint();
        }
    }
}
