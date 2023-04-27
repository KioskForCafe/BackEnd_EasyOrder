package com.kiosk.kioskback.service.implementation;

import java.util.List;

import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisBusinessResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisSaleResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisUserResponseDto;
import com.kiosk.kioskback.service.AnalysisService;

public class AnalysisServiceInplements implements AnalysisService {

    @Override
    public ResponseDto<GetAnalysisSaleResponseDto> getAnalysisSale(int storeId, String startedAt, String endedAt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAnalysisSale'");
    }

    @Override
    public ResponseDto<List<GetAnalysisBusinessResponseDto>> getAnalysisBusiness(int storeId, String startedAt,
            String endedAt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAnalysisBusiness'");
    }

    @Override
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(int storeId, String startedAt, String endedAt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAnalysisMenu'");
    }

    @Override
    public ResponseDto<GetAnalysisUserResponseDto> getAnalysisUser(int storeId, String startedAt, String endedAt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAnalysisUser'");
    }
    
}
