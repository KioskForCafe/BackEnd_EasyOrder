package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategoryEntity {

    private int menuCategoryId;
    private String menuCategoryName;
    private int menuCategoryPriority;
    
}
