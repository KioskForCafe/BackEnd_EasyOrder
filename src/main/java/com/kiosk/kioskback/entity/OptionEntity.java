package com.kiosk.kioskback.entity;

import java.util.ArrayList;
import java.util.List;

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
        if(patchMenuOptionDto.getOptionId() != null) this.optionId = patchMenuOptionDto.getOptionId();
        this.optionName = patchMenuOptionDto.getOptionName();
        this.optionPrice = patchMenuOptionDto.getOptionPrice();
        this.menuId = menuId;
    }

    public void patch(PatchMenuOptionDto patchMenuOptionDto) {
        this.optionName = patchMenuOptionDto.getOptionName();
        this.optionPrice = patchMenuOptionDto.getOptionPrice();
    }

    public static List<OptionEntity> copyList(List<PatchMenuOptionDto> patchMenuOptionDtoList, int menuId){
        List<OptionEntity> list = new ArrayList<>();

        for(PatchMenuOptionDto patchMenuOptionDto : patchMenuOptionDtoList){
            OptionEntity optionEntity = new OptionEntity(patchMenuOptionDto, menuId);
            list.add(optionEntity);
        }

        return list;
    }
    
}
