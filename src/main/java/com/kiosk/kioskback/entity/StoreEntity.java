package com.kiosk.kioskback.entity;

import com.kiosk.kioskback.dto.request.store.PatchStoreDto;
import com.kiosk.kioskback.dto.request.store.PostStoreDto;

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

    public StoreEntity(PostStoreDto dto, String userId){
        this.storeName = dto.getStoreName();
        this.storeOpenTime = dto.getStoreOpenTime();
        this.storeCloseTime = dto.getStoreCloseTime();
        this.storeImgUrl = dto.getStoreImgUrl();
        this.storeLogoUrl = dto.getStoreLogoUrl();
        this.userId = userId;
    }

    public void patch(PatchStoreDto dto){
        this.storeName = dto.getStoreName();
        this.storeOpenTime = dto.getStoreOpenTime();
        this.storeCloseTime = dto.getStoreCloseTime();
        this.storeImgUrl = dto.getStoreImgUrl();
        this.storeLogoUrl = dto.getStoreLogoUrl();
    }
}
