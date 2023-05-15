package com.kiosk.kioskback.dto.response.order;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.OptionEntity;
import com.kiosk.kioskback.entity.OrderDetailEntity;
import com.kiosk.kioskback.entity.OrderDetailLogEntity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "주문 내역 상세 확인 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderDetailResponseDto {
    String menuName;
    int menuPrice;
    int menuCount;
    List<GetOrderDetailOptionListDto> optionList;

    public GetOrderDetailResponseDto(OrderDetailLogEntity orderDetailLogEntity, List<GetOrderDetailOptionListDto> optionList) {
        this.menuName = orderDetailLogEntity.getMenuName();
        this.menuPrice = orderDetailLogEntity.getMenuPrice();
        this.menuCount = orderDetailLogEntity.getCount();
        this.optionList = optionList;
    }

    public List<GetOrderDetailResponseDto> copyList(List<OrderDetailLogEntity> orderDetailList) {
        List<GetOrderDetailResponseDto> list = new ArrayList<>();

        for (OrderDetailLogEntity orderDetailLogEntity: orderDetailList) {
            GetOrderDetailResponseDto dto = new GetOrderDetailResponseDto(orderDetailLogEntity);
            list.add(dto);
        }
    
        return list;
    }

}
