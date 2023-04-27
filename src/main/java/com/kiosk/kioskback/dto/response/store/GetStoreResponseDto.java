package com.kiosk.kioskback.dto.response.store;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.StoreEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "매장 리스트 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStoreResponseDto {
    @ApiModelProperty(value = "매장 번호", example = "1" ,required = true)
    private int storeId;
    
    @ApiModelProperty(value = "매장 이름", example = "스타벅스 1호점" ,required = true)
    private String storeName;
    
    @ApiModelProperty(value = "매장 오픈시간", example = "8" ,required = true)
    private int storeOpenTime;
    
    @ApiModelProperty(value = "매장 마감시간", example = "22" ,required = true)
    private int storeCloseTime;
    
    @ApiModelProperty(value = "매장 이미지 URL", example = "http://~" ,required = true)
    private String storeImgUrl;
    
    @ApiModelProperty(value = "매장 로고 URL", example = "http://~" ,required = true)
    private String storeLogoUrl;

    public GetStoreResponseDto(StoreEntity storeEntity){
        this.storeId = storeEntity.getStoreId();
        this.storeName = storeEntity.getStoreName();
        this.storeOpenTime = storeEntity.getStoreOpenTime();
        this.storeCloseTime = storeEntity.getStoreCloseTime();
        this.storeImgUrl = storeEntity.getStoreImgUrl();
        this.storeLogoUrl = storeEntity.getStoreLogoUrl();
    }

    public static List<GetStoreResponseDto> copyList(List<StoreEntity> storeEntities){
        List<GetStoreResponseDto> list = new ArrayList<>();

        for(StoreEntity storeEntity : storeEntities){
            GetStoreResponseDto dto = new GetStoreResponseDto(storeEntity);
            list.add(dto);
        }

        return list;
    }

}
