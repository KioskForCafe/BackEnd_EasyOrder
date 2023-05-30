package com.kiosk.kioskback.entity;

import com.kiosk.kioskback.dto.request.order.PostOrderDetailOptionDto;

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
@Entity(name = "`OptionLog`")
@Table(name = "`OptionLog`")
public class OptionLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionLogId;
    private int optionId;
    private String optionName;
    private int optionPrice;
    private int orderDetailLogId;

    public OptionLogEntity(PostOrderDetailOptionDto option, int orderDetailLogId) {
        this.optionId = option.getOptionId();
        this.optionName = option.getOptionName();
        this.optionPrice = option.getOptionPrice();
        this.orderDetailLogId = orderDetailLogId;
    }
}
