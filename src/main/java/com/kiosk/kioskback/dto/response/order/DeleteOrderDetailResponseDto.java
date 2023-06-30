package com.kiosk.kioskback.dto.response.order;

import java.util.List;

import com.kiosk.kioskback.entity.OptionEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "장바구니 메뉴 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderDetailResponseDto {

    @Schema(description = "상세 주문 번호", example = "1", required = true)
    private int orderDetailId;

    @Schema(description = "메뉴 이름", example = "아메리카노", required = true)
    private String menuName;

    @Schema(description = "메뉴 가격", example = "2000", required = true)
    private int menuPrice;

    // todo : 엔티티를 바로 사용하였음
    @Schema(description = "옵션 리스트", example = "list", required = false)
    private List<OptionEntity> optionList;

    @Schema(description = "상세 주문 수량", example = "3", required = true)
    private int orderDetailCount;

    
}
