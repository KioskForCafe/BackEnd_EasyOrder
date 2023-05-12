package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.PointEntity;

@Repository
public interface PointRepository extends JpaRepository<PointEntity,String>{
    
}
