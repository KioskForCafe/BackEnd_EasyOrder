package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {

    private int menuId;
    private String menuName;
    private int menuPrice;
    private String menuImg;
    private int menuState;
    private int menuCategoryId;
    private int storeId;
    
}
