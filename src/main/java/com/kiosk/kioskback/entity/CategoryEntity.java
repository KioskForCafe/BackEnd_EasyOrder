package com.kiosk.kioskback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Category")
@Table(name = "Category")
public class CategoryEntity {

    @Id
    private int categoryId;
    private String categoryName;
    private int categoryPriority;
    private int storeId;
    
}
