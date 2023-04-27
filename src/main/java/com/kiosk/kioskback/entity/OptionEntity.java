package com.kiosk.kioskback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "menu_menu_id")
    private int menuId;
    
}
