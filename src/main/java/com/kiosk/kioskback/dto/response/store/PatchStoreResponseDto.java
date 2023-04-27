package com.kiosk.kioskback.dto.response.store;

import com.kiosk.kioskback.entity.StoreEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "매장 수정 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchStoreResponseDto {
    @ApiModelProperty(value = "매장 번호", example = "1", required = true)
    private int storeId;

    @ApiModelProperty(value = "매장 이름", example = "스타벅스", required = true)
    private String storeName;
    
    @ApiModelProperty(value = "매장 오픈시간", example = "8")
    private int storeOpenTime;

    @ApiModelProperty(value = "매장 마감시간", example = "22")
    private int storeCloseTime;
    
    @ApiModelProperty(value = "매장 로고 URL", example = "http://~")
    private String storeLogoUrl;

    @ApiModelProperty(value = "매장 이미지 URL", example = "http://~")
    private String storeImgUrl;

    public PatchStoreResponseDto(StoreEntity storeEntity){
        this.storeId = storeEntity.getStoreId();
        this.storeName = storeEntity.getStoreName();
        this.storeOpenTime = storeEntity.getStoreOpenTime();
        this.storeCloseTime = storeEntity.getStoreCloseTime();
        this.storeImgUrl = storeEntity.getStoreImgUrl();
        this.storeLogoUrl = storeEntity.getStoreLogoUrl();
    }
}
