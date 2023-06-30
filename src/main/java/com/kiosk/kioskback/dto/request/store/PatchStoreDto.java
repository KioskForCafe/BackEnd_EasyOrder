package com.kiosk.kioskback.dto.request.store;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "매장 수정 Request Body")
@Data
@NoArgsConstructor
public class PatchStoreDto {
    @Schema(description = "매장 번호", example = "1", required = true)
    @Min(1)
    private int storeId;
    
    @Schema(description = "매장 이름", example = "스타벅스", required = true)
    @NotBlank
    private String storeName;
    
    @Schema(description = "매장 오픈시간", example = "8")
    @Min(0)
    @Max(23)
    private int storeOpenTime;
    
    @Schema(description = "매장 마감시간", example = "22")
    @Min(0)
    @Max(23)
    private int storeCloseTime;

    @Schema(description = "매장 로고 URL", example = "http://~", required = true)
    private String storeLogoUrl;
    
    @Schema(description = "매장 이미지 URL", example = "스타벅스", required = true)
    private String storeImgUrl;
}