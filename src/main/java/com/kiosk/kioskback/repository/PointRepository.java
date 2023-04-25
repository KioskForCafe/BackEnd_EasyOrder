package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.PointEntity;

public interface PointRepository extends JpaRepository<PointEntity,String>{
    
}
