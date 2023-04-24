package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreEntity {
    private int storeId;
    private String storeName;
    private int storeOpenTime;
    private int storeCloseTime;
    private String storeImgUrl;
    private String storeLogoUrl;
    private String userId;
}
