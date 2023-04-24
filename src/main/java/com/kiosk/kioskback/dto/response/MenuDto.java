package com.kiosk.kioskback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private int menuId;
    private String menuName;
    private int menuPrice;
    private int menuImgUrl;
}
