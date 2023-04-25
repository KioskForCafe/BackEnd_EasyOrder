package com.kiosk.kioskback.dto.response;

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
public class OptionsDto {
    
    @ApiModelProperty(value = "옵션 번호", required = true)
    private int optionId;

    @ApiModelProperty(value = "옵션 이름", required = true)
    private String optionName;

    @ApiModelProperty(value = "옵션 가격", required = true)
    private int optionPrice;

    @ApiModelProperty(value = "메뉴 번호", required = true)
    private int menuId;
    
    public OptionsDto(OptionEntity optionEntity) {
        this.optionId = optionEntity.getOptionId();
        this.optionName = optionEntity.getOptionName();
        this.optionPrice = optionEntity.getOptionPrice();
        this.menuId = optionEntity.getMenuId();
    }
}
