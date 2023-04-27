package com.kiosk.kioskback.entity.primaryKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class OrderDetailOptionPK implements Serializable{
    
    @Column(name = "order_detail_id")
    private int orderDetailId;
    @Column(name = "option_id")
    private int optionId;
}
