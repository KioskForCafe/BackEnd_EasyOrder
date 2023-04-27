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
@Entity(name = "Menu")
@Table(name = "Menu")
public class MenuEntity {

    @Id
    private int menuId;
    private String menuName;
    private int menuPrice;
    private String menuImgUrl;
    private boolean menuState;
    private int categoryId;
    private int storeId;
    
}
