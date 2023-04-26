package com.kiosk.kioskback.service;

import java.util.Date;

import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;

public interface AnalysisService {
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(String userId, int storeId, Date startedAt, Date endedAt);
}
