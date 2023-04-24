package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    private int categoryId;
    private String categoryName;
    private int categoryPriority;
    
}
