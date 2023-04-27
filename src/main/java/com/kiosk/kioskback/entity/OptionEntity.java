package com.kiosk.kioskback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Option")
@Table(name = "Option")
public class OptionEntity {

    @Id
    private int optionId;

    private String optionName;

    private int optionPrice;

    // @Column(name = "menu_id")
    private int menuId;

    // @ManyToOne
    // @JoinColumn(name = "menu_id")
    // private MenuEntity menuId;
    
}
