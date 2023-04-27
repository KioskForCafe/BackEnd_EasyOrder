package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.MenuEntity;

import jakarta.transaction.Transactional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {

    public MenuEntity findByMenuId(int menuId);
    public List<MenuEntity> findByStoreIdAndCategoryName(int storeId, String categoryName);

    @Transactional
    public void deleteByCategoryId(int categoryId);
    
}
