package com.kiosk.kioskback.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.order.GetOrderListResponseDto;
import com.kiosk.kioskback.entity.OrderLogEntity;
import com.kiosk.kioskback.repository.OrderLogRepository;
import com.kiosk.kioskback.service.OrderLogService;

public class OrderLogServiceImplements implements OrderLogService {

    @Autowired OrderLogRepository orderLogRepository;

    @Override
    public ResponseDto<List<GetOrderListResponseDto>> getOrderLogList() {

        List<GetOrderListResponseDto> data = null;

        try {
            List<OrderLogEntity> orderLogList = orderLogRepository.findAll();

            // order_log의 데이터를 전부 가져온다
            // order_Log_Id를 이용해 order_detail_log의 데이터를 전부 가져온다

            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
