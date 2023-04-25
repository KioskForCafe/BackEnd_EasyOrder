package com.kiosk.kioskback.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="사용자 아이디 중복체크 Request Body")
@Data
@NoArgsConstructor
public class PostCheckIdDuplicateDto {
    @ApiModelProperty(value="사용자 아이디", example="qwerqwer", required=true)
    @NotBlank
    @Id
    private String userId;
}
