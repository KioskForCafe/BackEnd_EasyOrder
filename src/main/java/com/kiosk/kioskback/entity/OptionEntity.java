package com.kiosk.kioskback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OptionEntity {

    @Id
    private int optionId;
    private String optionName;
    private int optionPrice;
    private int menuId;
    
}
