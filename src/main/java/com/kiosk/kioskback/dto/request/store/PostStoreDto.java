package com.kiosk.kioskback.dto.request.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "매장 등록 Request Body")
@Data
@NoArgsConstructor
public class PostStoreDto {
    @ApiModelProperty(value = "매장 이름", example = "스타벅스", required = true)
    @NotBlank
    private String storeName;

    @ApiModelProperty(value = "매장 오픈시간", example = "8")
    @Min(0)
    @Max(23)
    private int storeOpenTime;
    
    @ApiModelProperty(value = "매장 마감시간", example = "22")
    @Min(0)
    @Max(23)
    private int storeCloseTime;

    @ApiModelProperty(value = "매장 로고 URL", example = "http://~", required = true)
    private String storeLogoUrl;
    
    @ApiModelProperty(value = "매장 이미지 URL", example = "http://~", required = true)
    private String storeImgUrl;
}
