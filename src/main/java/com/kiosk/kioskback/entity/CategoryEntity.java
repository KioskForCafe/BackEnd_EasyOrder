package com.kiosk.kioskback.entity;

import com.kiosk.kioskback.dto.request.category.PatchCategoryDto;
import com.kiosk.kioskback.dto.request.category.PostCategoryDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Category")
@Table(name="Category")
public class CategoryEntity {

    @Id
    private int categoryId;
    private String categoryName;
    private int categoryPriority;
    private int storeId;

    public CategoryEntity(PostCategoryDto postCategoryDto) {
        this.categoryName = postCategoryDto.getCategoryName();
        this.categoryPriority = postCategoryDto.getCategoryPriority();
        this.storeId = postCategoryDto.getStoreId();
    }

    public void patch(PatchCategoryDto dto) {
        this.categoryName = dto.getCategoryName();
        
    }
    
}
