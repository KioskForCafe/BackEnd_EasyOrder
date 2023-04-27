package com.kiosk.kioskback.entity;

import com.kiosk.kioskback.entity.primaryKey.OrderDetailOptionPK;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "OrderDetailOption")
@Table(name = "OrderDetailOption")
@IdClass(OrderDetailOptionPK.class)
public class OrderDetailOptionEntity {
    @Id
    private int orderDetailId;
    @Id
    private int optionId;
    
}
