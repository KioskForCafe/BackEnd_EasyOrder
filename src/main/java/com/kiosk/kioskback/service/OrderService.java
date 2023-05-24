package com.kiosk.kioskback.service;

import java.util.List;

import com.kiosk.kioskback.dto.request.order.PatchOrderDto;
import com.kiosk.kioskback.dto.request.order.PostOrderDto;
import com.kiosk.kioskback.dto.request.order.PostOrderLogDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.order.DeleteOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.GetOrderDetailResponseDto;
import com.kiosk.kioskback.dto.response.order.GetOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.PatchOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.PostOrderLogResponseDto;
import com.kiosk.kioskback.dto.response.order.PostOrderResponseDto;

public interface OrderService {

    public ResponseDto<List<GetOrderResponseDto>> getOrderList(String userId, int storeId, String orderState);
    public ResponseDto<List<GetOrderDetailResponseDto>> getOrderDetailList(String userId, int orderId);
    public ResponseDto<PostOrderResponseDto> postOrder(PostOrderDto dto);
    public ResponseDto<DeleteOrderResponseDto> deleteOrderDetail(int orderDetailId);
    public ResponseDto<List<PatchOrderResponseDto>> patchOrder(String userId, PatchOrderDto dto);
    public ResponseDto<PostOrderLogResponseDto> postOrderLog(PostOrderLogDto dto);
}
