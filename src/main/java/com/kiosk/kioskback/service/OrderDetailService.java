package com.kiosk.kioskback.service;

import com.kiosk.kioskback.dto.request.order.PostOrderDetailDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.order.PostOrderDetailResponseDto;

public interface OrderDetailService {

    public ResponseDto<PostOrderDetailResponseDto> postOrderDetail(PostOrderDetailDto dto);
    
}
