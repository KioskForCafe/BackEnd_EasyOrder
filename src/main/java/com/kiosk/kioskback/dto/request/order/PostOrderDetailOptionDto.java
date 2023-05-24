package com.kiosk.kioskback.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderDetailOptionDto {
    private int optionId;
    private String optionName;
    private int optionPrice;
}
