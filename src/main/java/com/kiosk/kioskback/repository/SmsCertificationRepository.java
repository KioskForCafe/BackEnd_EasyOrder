package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.SmsCertificationEntity;

public interface SmsCertificationRepository extends JpaRepository<SmsCertificationEntity,Integer>{
    
}
