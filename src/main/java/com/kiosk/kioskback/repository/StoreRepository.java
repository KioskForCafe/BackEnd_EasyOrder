package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity,Integer> {
    public List<StoreEntity> findByUserId(String userId);
    public StoreEntity findByStoreId(int storeId);
}
