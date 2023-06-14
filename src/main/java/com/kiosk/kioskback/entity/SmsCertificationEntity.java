package com.kiosk.kioskback.entity;

import java.util.Date;

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
@Entity(name = "SmsCertification")
@Table(name = "SmsCertification")
public class SmsCertificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int smsCertificationId;
    private String telNumber;
    private String authenticationCode;
    private Date createdAt;
    
    public SmsCertificationEntity(String telNumber, String authenticationCode){
        Date now = new Date();
        this.telNumber = telNumber;
        this.authenticationCode = authenticationCode;
        this.createdAt = now;
    }
}
