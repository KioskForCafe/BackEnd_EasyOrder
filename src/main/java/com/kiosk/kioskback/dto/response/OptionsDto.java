package com.kiosk.kioskback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsDto {
    
    private int optionId;
    private String optionName;
    private int optionPrice;
    private int menuId;
    
}
