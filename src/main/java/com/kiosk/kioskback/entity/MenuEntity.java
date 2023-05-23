package com.kiosk.kioskback.entity;

import java.util.List;

import com.kiosk.kioskback.dto.request.menu.PatchMenuDto;
import com.kiosk.kioskback.dto.request.menu.PostMenuDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;
    private String menuName;
    private int menuPrice;
    private String menuImgUrl;
    private boolean menuState;
    private Integer categoryId;
    private int storeId;

    public void patch(PatchMenuDto patchMenuDto) {
        this.menuName = patchMenuDto.getMenuName();
        this.menuPrice = patchMenuDto.getMenuPrice();
        this.menuImgUrl = patchMenuDto.getMenuImgUrl();
        this.menuState = patchMenuDto.getMenuState();
        this.categoryId = patchMenuDto.getCategoryId();
    }

}
