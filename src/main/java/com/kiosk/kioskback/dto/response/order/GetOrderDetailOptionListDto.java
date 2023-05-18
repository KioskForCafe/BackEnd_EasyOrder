package com.kiosk.kioskback.dto.response.order;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "주문 내역 상세 옵션 확인 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderDetailOptionListDto {
    String optionName;
    int optionPrice;

    public GetOrderDetailOptionListDto(OptionEntity optionEntity) {
        this.optionName = optionEntity.getOptionName();
        this.optionPrice = optionEntity.getOptionPrice();
    }

    public List<GetOrderDetailOptionListDto> copyOptionList(List<OptionEntity> optionList) {

        List<GetOrderDetailOptionListDto> list = new ArrayList<>();

        for (OptionEntity optionEntity: optionList) {
            GetOrderDetailOptionListDto dto = new GetOrderDetailOptionListDto(optionEntity);
            list.add(dto);
        }
    
        return list;
        
    }
}
