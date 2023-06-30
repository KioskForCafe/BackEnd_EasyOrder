package com.kiosk.kioskback.dto.response.order;

import java.util.List;

import javax.swing.text.html.Option;

import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;
import com.kiosk.kioskback.entity.OrderDetailEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "주문번호에 해당하는 상세주문 리스트 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderDetailResponseDto {
    private int menuId;
    private String menuName;
    private int menuPrice;
    private int categoryId;
    private String categoryName;
    private int priceWithOption;
    private int count;
    private List<GetOrderDetailOptionListDto> optionList;

    public GetOrderDetailResponseDto(OrderDetailEntity orderDetailEntity, MenuEntity menuEntity, String categoryName , List<OptionEntity> optionList){
        this.menuId = orderDetailEntity.getMenuId();
        this.menuName = menuEntity.getMenuName();
        this.menuPrice = menuEntity.getMenuPrice();
        this.categoryId = menuEntity.getCategoryId();
        this.categoryName = categoryName;
        int sumOptionPrice = 0;
        for(OptionEntity optionEntity : optionList){
            sumOptionPrice +=optionEntity.getOptionPrice();
        }
        this.priceWithOption = menuEntity.getMenuPrice() + sumOptionPrice;
        this.count = orderDetailEntity.getCount();
        this.optionList = GetOrderDetailOptionListDto.copyList(optionList);
    }

}
