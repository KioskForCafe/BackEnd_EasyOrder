package com.kiosk.kioskback.dto.request.order;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOrderLogDto {

    private List<PostOrderDetailLogDto> orderDetail;
    private String userId;
    private String userName;
    private String telNumber;
    private int storeId;
    private String storeName;
    private int orderId;
    private Date createdAt;
    private int totalPrice;

    
}
