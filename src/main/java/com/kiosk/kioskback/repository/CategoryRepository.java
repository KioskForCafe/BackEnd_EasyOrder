package com.kiosk.kioskback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    public List<CategoryEntity> findByStoreIdOrderByCategoryPriorityAsc(int storeId);

    public CategoryEntity findByCategoryId(int categoryId);
    
}
