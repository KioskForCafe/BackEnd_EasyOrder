package com.kiosk.kioskback.entity;

import java.util.List;

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
@Entity(name = "menu")
@Table(name = "menu")
public class MenuEntity {

    @Id
    private int menuId;
    private String menuName;
    private int menuPrice;
    private String menuImg;
    private boolean menuState;
    private int categoryId;
    private int storeId;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OptionEntity> getOptions;

    // public MenuEntity(PostMenuDto postMenuDto) {
    //     this.menuId = postMenuDto.getMenuDto().getMenuId();
    //     this.menuName = postMenuDto.getMenuDto().getMenuName();
    //     this.menuPrice = postMenuDto.getMenuDto().getMenuPrice();
    //     this.menuImg = postMenuDto.getMenuDto().getMenuImgUrl();
    //     this.menuState = postMenuDto.getMenuDto().isMenuState();
    //     this.categoryId = postMenuDto.getMenuDto().getCategoryId();
    //     this.storeId = postMenuDto.getStoreId();
    // }


}
