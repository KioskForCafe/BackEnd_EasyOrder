package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.AlarmEntity;

@Repository
public interface AlarmRepository extends JpaRepository<AlarmEntity, Integer> {
    public List<AlarmEntity> findByStoreId(int storeId);
}
