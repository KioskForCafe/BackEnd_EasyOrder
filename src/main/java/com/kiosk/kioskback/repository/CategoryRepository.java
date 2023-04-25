package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>{
    
}
