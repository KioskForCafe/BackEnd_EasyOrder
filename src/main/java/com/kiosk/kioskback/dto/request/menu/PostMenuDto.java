package com.kiosk.kioskback.dto.request.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.dto.response.MenuResponseDto;
import com.kiosk.kioskback.dto.response.OptionResponseDto;
import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상품 추가 Request Body")
@Data
@NoArgsConstructor
public class PostMenuDto {
    
    @ApiModelProperty(value = "매장 번호", example = "1", required = true)
    @NotBlank
    private int storeId;

    @ApiModelProperty(value = "상품 정보", required = true)
    private MenuResponseDto menuDto;

}
