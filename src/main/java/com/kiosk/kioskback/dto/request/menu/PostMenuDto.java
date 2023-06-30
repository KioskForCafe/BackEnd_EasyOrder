package com.kiosk.kioskback.dto.request.menu;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "상품 추가 Request Body")
@Data
@NoArgsConstructor
public class PostMenuDto {
    
    @Schema(description = "매장 번호", example = "1", required = true)
    @Min(1)
    private int storeId;

    @Schema(description = "카테고리 번호", required = true)
    private Integer categoryId;

    @Schema(description = "메뉴 이름", required = true)
    @NotBlank
    private String menuName;

    @Schema(description = "메뉴 가격", required = true)
    @Min(0)
    private int menuPrice;

    @Schema(description = "메뉴 상태(품절/판매)", required = true)
    @NotNull
    private Boolean menuState;

    @Schema(description = "메뉴 이미지", required = false)
    private String menuImgUrl;

    @Schema(description = "옵션 정보", required = false)
    private List<PostMenuOptionDto> optionList;

}
