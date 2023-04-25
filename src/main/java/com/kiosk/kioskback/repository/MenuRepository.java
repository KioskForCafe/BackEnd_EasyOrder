package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, String> {

    public MenuEntity findByMenuId(int menuId);
    public List<MenuEntity> findByStoreIdAndCategoryName(int storeId, String categoryName);
    
}
