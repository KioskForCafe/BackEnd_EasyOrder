package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.dto.response.MenuResponseDto;
import com.kiosk.kioskback.dto.response.OptionResponseDto;
import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "메뉴 상세 정보 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuDetailResponseDto {
    @ApiModelProperty(value = "상품 정보", required = true)
    private MenuResponseDto menuDto;

    // @ApiModelProperty(value = "옵션 정보 리스트", required = true)
    // private List<OptionDto> optionList;

    public GetMenuDetailResponseDto(MenuEntity menuEntity, List<OptionEntity> optionList) {
        // todo : this.menuDto = new MenuResponseDto(menuEntity, OptionResponseDto.copyList(optionList));
        this.menuDto.setOptionList(copyList(optionList));
        this.menuDto = new MenuResponseDto(menuEntity, this.menuDto.getOptionList());
    }
    
    // todo : OptionResponseDto로 변환하는 내용으로 필요시 OptionResponseDto에 있으면 될것으로 보임
    public static List<OptionResponseDto> copyList(List<OptionEntity> optionEntityList) {

        List<OptionResponseDto> list = new ArrayList<>();
    
        for (OptionEntity optionEntity: optionEntityList) {
            OptionResponseDto dto = new OptionResponseDto(optionEntity);
            list.add(dto);
        }
    
        return list;
    
    }
}
