package com.kiosk.kioskback.dto.response.analysis;

import java.util.ArrayList;
import java.util.List;

import com.kiosk.kioskback.entity.resultSet.UserTop10ResultSet;

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

    @ApiModelProperty(value = "회원 번호", example = "1", required = true)
    private String userId;

    @ApiModelProperty(value = "회원 이름", example = "홍길동", required = true)
    private String userName;

    @ApiModelProperty(value = "회원 전화번호", example = "010-0000-0000", required = true)
    private String telNumber;

    @ApiModelProperty(value = "방문수", example = "10", required = true)
    private int visitedCount;

    @ApiModelProperty(value = "적립 포인트", example = "100", required = true)
    private int point;

    @ApiModelProperty(value = "결제 금액", example = "10000", required = true)
    private int amountPayment;

    public UserTop10ResponseDto(UserTop10ResultSet userTop10ResultSet) {
        this.userId = userTop10ResultSet.getUserId();
        this.userName = userTop10ResultSet.getUserName();
        this.telNumber = userTop10ResultSet.getTelNumber();
        this.visitedCount = userTop10ResultSet.getVisitedCount();
        this.point = 0;
        if (userTop10ResultSet.getPoint() != null) {
            this.point = userTop10ResultSet.getPoint();
        }
        this.amountPayment = userTop10ResultSet.getAmountPayment();
    }

    public static List<UserTop10ResponseDto> copy(List<UserTop10ResultSet> userTop10ResultSets) {
        List<UserTop10ResponseDto> list = new ArrayList<>();

        for (UserTop10ResultSet userTop10ResultSet : userTop10ResultSets) {
            UserTop10ResponseDto userTop10ResponseDto = new UserTop10ResponseDto(userTop10ResultSet);
            list.add(userTop10ResponseDto);
        }
        return list;
    }

}
