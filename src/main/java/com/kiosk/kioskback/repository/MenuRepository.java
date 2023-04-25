package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer>{
    
}
