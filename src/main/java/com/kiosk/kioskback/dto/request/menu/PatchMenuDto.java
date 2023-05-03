package com.kiosk.kioskback.dto.request.menu;

import java.util.List;

import com.kiosk.kioskback.dto.response.MenuResponseDto;
import com.kiosk.kioskback.dto.response.OptionResponseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "상품 수정 Request Body")
@Data
@NoArgsConstructor
public class PatchMenuDto {

    // todo : RequestDto에 MenuResponseDto가 포함되어있음
    // todo : API 문서에 categoryId가 빠진것 같음 수정이 필요할듯함(상품수정시 상품이 어떤 카테고리의 상품인지 수정가능하도록 하려면 필요)
    // todo : MenuResponseDto안에 optionList가 포함되어 있어 중복임

    // todo : 논의할 점 : MenuResponseDto를 쓰지 않는 것이 어떤지
    // todo : 이유 : Post, Patch 등 주는 데이터는 다르고 같은 MenuResponseDto를 쓰기에 반드시 줘야할 데이터가 다르다.
    // todo :        현재는 Swagger에 모두 반드시 필요한 데이터로 보여지기 때문에 잘못 되어있다.

    @ApiModelProperty(value = "메뉴 정보", required = true)
    private MenuResponseDto menuDto;

    @ApiModelProperty(value = "옵션 정보", required = true)
    private List<OptionResponseDto> optionList;

}
