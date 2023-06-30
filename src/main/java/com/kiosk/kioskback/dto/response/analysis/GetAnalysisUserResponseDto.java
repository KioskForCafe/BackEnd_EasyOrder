package com.kiosk.kioskback.dto.response.analysis;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "기간동안의 매장 방문 회원 정보 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAnalysisUserResponseDto {

    @Schema(description = "총 회원수", example = "100", required = true)
    private int totalVisitedUserCount;

    @Schema(description = "신규 회원수", example = "10", required = true)
    private int newVisitedUserCount;

    @Schema(description = "회원 정보 리스트", required = true)
    private List<UserTop10ResponseDto> userTop10List;

}
