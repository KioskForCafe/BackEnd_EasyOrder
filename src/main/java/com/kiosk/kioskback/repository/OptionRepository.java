package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.OptionEntity;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Integer> {
    public List<OptionEntity> findByMenuId(int menuId);
}
