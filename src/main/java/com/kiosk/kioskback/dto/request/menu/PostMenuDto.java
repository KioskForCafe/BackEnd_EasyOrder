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
    
    // todo : requestDto인데 상품정보는 MenuResponseDto와 연결되어 있음
    // todo : API문서와 비교시 MenuResponseDto의 정보가 다름 
    // todo : menuId는 주지 않는다, MenuResponseDto에 필수로 되어있음
    // todo : MenuResponseDto에 storeId가 중복으로 들어있음
    // todo : MenuResponseDto에 OptionDto

    // todo : 적다보니 menuResponseDto로 만들어야 할지 생각해 봐야할것 같다.

    @ApiModelProperty(value = "매장 번호", example = "1", required = true)
    @NotBlank
    private int storeId;

    @ApiModelProperty(value = "상품 정보", required = true)
    private MenuResponseDto menuDto;

}
