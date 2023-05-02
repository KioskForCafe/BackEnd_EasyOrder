package com.kiosk.kioskback.dto.response.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mapping.AccessOptions.GetOptions;

import com.kiosk.kioskback.dto.request.menu.PostMenuDto;
import com.kiosk.kioskback.dto.response.MenuResponseDto;
import com.kiosk.kioskback.dto.response.OptionResponseDto;
import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "메뉴 추가하기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMenuResponseDto {

    // todo : 메뉴 등록 후 리스트를 반환
    // todo : 리스트 반환에는 option은 필요 없어 보임
    // todo : MenuResponseDto의 필요 없는 데이터들이 보임
    @ApiModelProperty(value = "메뉴 전체 정보", required = true)
    private MenuResponseDto menuDto;

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
