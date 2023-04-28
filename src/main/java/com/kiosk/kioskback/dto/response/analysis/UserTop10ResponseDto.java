package com.kiosk.kioskback.dto.response.analysis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "기간동안의 회원 정보 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTop10ResponseDto {
    
    @ApiModelProperty(value = "회원 이름", example = "홍길동", required = false)
    private String userName;

    @ApiModelProperty(value = "회원 전화번호", example = "010-0000-0000",  required = true)
    private String telNumber;

    @ApiModelProperty(value = "방문수", example = "10", required = true)
    private int visitedCount;

    @ApiModelProperty(value = "적립 포인트", example = "100", required = true)
    private int point;

    @ApiModelProperty(value = "결제 금액", example = "10000", required = true)
    private int amountPayment;

}
