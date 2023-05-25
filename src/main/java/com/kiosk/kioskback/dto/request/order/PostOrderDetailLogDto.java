package com.kiosk.kioskback.dto.request.order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOrderDetailLogDto {

    private int menuId;
    private String menuName;
    private int menuPrice;
    private int categoryId;
    private String categoryName;
    private List<PostOrderDetailOptionDto> optionList;
    private int priceWithOption;
    private int storeId;
    private String storeName;
    private int count;

}
