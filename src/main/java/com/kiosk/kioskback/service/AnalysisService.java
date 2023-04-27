package com.kiosk.kioskback.service;

<<<<<<< HEAD
=======
import java.util.Date;

>>>>>>> 86fbc6ce98590ae54432058a2d23cb0d4b3954d1
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.analysis.GetAnalysisMenuResponseDto;

public interface AnalysisService {
<<<<<<< HEAD
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(String userId, int storeId, int startedAt, int endedAt);
=======
    public ResponseDto<GetAnalysisMenuResponseDto> getAnalysisMenu(String userId, int storeId, Date startedAt, Date endedAt);
>>>>>>> 86fbc6ce98590ae54432058a2d23cb0d4b3954d1
}
