package com.kiosk.kioskback.service;

import java.util.Date;
import java.util.List;

import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisBusinessResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisSaleResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisUserResponseDto;

public interface AnalysisService {
    public ResponseDto<GetAnalysisSaleResponseDto> getAnalysisSale(String userId, int storeId, String startedAt, String endedAt);
    public ResponseDto<List<GetAnalysisBusinessResponseDto>> getAnalysisBusiness(String userId, int storeId, String startedAt, String endedAt);
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(String userId, int storeId, String startedAt, String endedAt);
    public ResponseDto<GetAnalysisUserResponseDto> getAnalysisUser(String userId, int storeId, String startedAt, String endedAt);
}
