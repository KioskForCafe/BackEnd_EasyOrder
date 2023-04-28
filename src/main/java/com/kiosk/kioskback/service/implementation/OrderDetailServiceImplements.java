package com.kiosk.kioskback.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.order.PostOrderDetailDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.order.DeleteOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.PostOrderDetailResponseDto;
import com.kiosk.kioskback.entity.OrderDetailEntity;
import com.kiosk.kioskback.repository.OrderDetailRepository;
import com.kiosk.kioskback.service.OrderDetailService;

@Service
public class OrderDetailServiceImplements implements OrderDetailService {

    @Autowired private OrderDetailRepository orderDetailRepository;

    public ResponseDto<PostOrderDetailResponseDto> postOrderDetail(PostOrderDetailDto dto) {

        PostOrderDetailResponseDto data = null;

        try {

            OrderDetailEntity orderDetailEntity = new OrderDetailEntity(dto);
            orderDetailRepository.save(orderDetailEntity);

            data = new PostOrderDetailResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteOrderResponseDto> deleteOrderDetail(int orderDetailId) {
        
        DeleteOrderResponseDto data = null;

        try {

            OrderDetailEntity orderDetailEntity = orderDetailRepository.findByOrderDetailId(orderDetailId);
            if (orderDetailEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER_DETAIL_ID);

            orderDetailRepository.delete(orderDetailEntity);

            data = new DeleteOrderResponseDto();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
