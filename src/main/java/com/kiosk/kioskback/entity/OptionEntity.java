package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionEntity {

    private int optionId;
    private String optionName;
    private int optionPrice;
    private int menuId;
    
}
