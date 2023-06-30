package com.kiosk.kioskback.dto.response.store;

import com.kiosk.kioskback.entity.StoreEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "매장 정보 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStoreResponseDto {
    @Schema(description = "매장 번호", example = "1" ,required = true)
    private int storeId;
    
    @Schema(description = "매장 이름", example = "스타벅스 1호점" ,required = true)
    private String storeName;
    
    @Schema(description = "매장 오픈시간", example = "8" ,required = true)
    private int storeOpenTime;
    
    @Schema(description = "매장 마감시간", example = "22" ,required = true)
    private int storeCloseTime;
    
    @Schema(description = "매장 이미지 URL", example = "http://~" ,required = true)
    private String storeImgUrl;
    
    @Schema(description = "매장 로고 URL", example = "http://~" ,required = true)
    private String storeLogoUrl;

    public GetStoreResponseDto(StoreEntity storeEntity){
        this.storeId = storeEntity.getStoreId();
        this.storeName = storeEntity.getStoreName();
        this.storeOpenTime = storeEntity.getStoreOpenTime();
        this.storeCloseTime = storeEntity.getStoreCloseTime();
        this.storeImgUrl = storeEntity.getStoreImgUrl();
        this.storeLogoUrl = storeEntity.getStoreLogoUrl();
    }
}
