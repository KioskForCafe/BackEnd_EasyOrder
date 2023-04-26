package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity,Integer> {
    public StoreEntity findByUserId(String userId);
}
