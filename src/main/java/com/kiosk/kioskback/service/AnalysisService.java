package com.kiosk.kioskback.service;

import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;

public interface AnalysisService {
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(String userId, int storeId, int startedAt, int endedAt);
}
