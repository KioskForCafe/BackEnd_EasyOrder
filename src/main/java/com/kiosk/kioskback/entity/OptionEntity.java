package com.kiosk.kioskback.entity;

import com.kiosk.kioskback.dto.request.menu.PatchMenuOptionDto;
import com.kiosk.kioskback.dto.request.menu.PostMenuOptionDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "`Option`")
@Table(name = "`Option`")
public class OptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;

    private String optionName;

    private int optionPrice;

    private int menuId;

    public OptionEntity(PostMenuOptionDto postMenuOptionDto, int menuId){
        this.optionName = postMenuOptionDto.getOptionName();
        this.optionPrice = postMenuOptionDto.getOptionPrice();
        this.menuId = menuId;
    }

    public OptionEntity(PatchMenuOptionDto patchMenuOptionDto, int menuId) {
        this.optionName = patchMenuOptionDto.getOptionName();
        this.optionPrice = patchMenuOptionDto.getOptionPrice();
        this.menuId = menuId;
    }

    public void patch(PatchMenuOptionDto patchMenuOptionDto) {
        this.optionName = patchMenuOptionDto.getOptionName();
        this.optionPrice = patchMenuOptionDto.getOptionPrice();
    }
    
}
