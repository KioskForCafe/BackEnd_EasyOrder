package com.kiosk.kioskback.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderStateResponseDto {
    String orderState;
    int orderStateCount;
}
