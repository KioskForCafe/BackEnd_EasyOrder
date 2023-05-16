package com.kiosk.kioskback.dto.response.order;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "주문번호에 해당하는 상세주문 리스트 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderDetailResponseDto {
    private String menuName;
    private int count;
    private List<String> optionList;

}
