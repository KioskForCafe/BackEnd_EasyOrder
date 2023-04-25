package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.OptionEntity;

public interface OptionRepository extends JpaRepository<OptionEntity, Integer>{
    
}
