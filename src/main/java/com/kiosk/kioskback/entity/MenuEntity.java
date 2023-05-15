package com.kiosk.kioskback.entity;

import java.util.List;

import com.kiosk.kioskback.dto.request.menu.PatchMenuDto;
import com.kiosk.kioskback.dto.request.menu.PostMenuDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private int menuId;
    private String menuName;
    private int menuPrice;
    private String menuImgUrl;
    private boolean menuState;
    private int categoryId;
    private int storeId;

    // @OneToMany(mappedBy = "menu_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private List<OptionEntity> getOptions;

    // public MenuEntity(PostMenuDto postMenuDto) {
    //     this.menuId = postMenuDto.getMenuId();
    //     this.menuName = postMenuDto.getMenuDto().getMenuName();
    //     this.menuPrice = postMenuDto.getMenuDto().getMenuPrice();
    //     this.menuImg = postMenuDto.getMenuDto().getMenuImgUrl();
    //     this.menuState = postMenuDto.getMenuDto().isMenuState();
    //     this.categoryId = postMenuDto.getMenuDto().getCategoryId();
    //     this.storeId = postMenuDto.getStoreId();
    // }

    public void patch(PatchMenuDto patchMenuDto) {
        this.menuName = patchMenuDto.getMenuName();
        this.menuPrice = patchMenuDto.getMenuPrice();
        this.menuImgUrl = patchMenuDto.getMenuImgUrl();
        this.menuState = patchMenuDto.getMenuState();
        this.categoryId = patchMenuDto.getCategoryId();
    }

}
