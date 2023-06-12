package com.kiosk.kioskback.entity;

import java.util.Date;

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
    private Date createdAt;

    public PointEntity(PostPointDto dto){
        Date now = new Date();
        this.telNumber = dto.getTelNumber();
        this.value = dto.getValue();
        this.type = dto.getType();
        this.currentPoint = dto.getCurrentPoint();
        this.createdAt = now;
    }
    
    public PointEntity(int currentPoint, PostPointDto dto){
        Date now = new Date();
        this.telNumber = dto.getTelNumber();
        this.value = dto.getValue();
        boolean isType = dto.getType();
        this.createdAt = now;
        this.type = isType;
        //^ type = true : 적립, false : 사용
        if(isType){
            this.currentPoint = currentPoint + dto.getCurrentPoint();
        }else{
            this.currentPoint = currentPoint - dto.getCurrentPoint();
        }
    }
}
