package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "옵션 정보 Format")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMenuDetailOptionResponseDto {
    @ApiModelProperty(value = "옵션 번호", required = true)
    private int optionId;

    @ApiModelProperty(value = "옵션 이름", required = true)
    private String optionName;

    @ApiModelProperty(value = "옵션 가격", required = true)
    private int optionPrice;

    public GetMenuDetailOptionResponseDto(OptionEntity optionEntity) {
        this.optionId = optionEntity.getOptionId();
        this.optionName = optionEntity.getOptionName();
        this.optionPrice = optionEntity.getOptionPrice();
    }

    public static List<GetMenuDetailOptionResponseDto> copyList(List<OptionEntity> optionList){
        List<GetMenuDetailOptionResponseDto> list = new ArrayList<>();

        for (OptionEntity optionEntity: optionList) {
            GetMenuDetailOptionResponseDto dto = new GetMenuDetailOptionResponseDto(optionEntity);
            list.add(dto);
        }
    
        return list;

    }
}
