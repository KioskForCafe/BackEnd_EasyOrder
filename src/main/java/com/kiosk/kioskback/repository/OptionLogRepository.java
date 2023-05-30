package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.OptionLogEntity;

public interface OptionLogRepository extends JpaRepository<OptionLogEntity,Integer> {
    
}
