package com.kiosk.kioskback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.order.PatchOrderDto;
import com.kiosk.kioskback.dto.request.order.PostOrderDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.order.DeleteOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.GetOrderDetailResponseDto;
import com.kiosk.kioskback.dto.response.order.GetOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.PatchOrderResponseDto;
import com.kiosk.kioskback.dto.response.order.PostOrderResponseDto;
import com.kiosk.kioskback.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;

@Api(description = "주문 모듈")
@RestController
@RequestMapping(ApiPattern.ORDER)
public class OrderController {

    @Autowired private OrderService orderService;

    private final String GET_ORDER_LIST = "/list/{storeId}/{orderState}";
    private final String GET_ORDER_DETAIL_LIST = "/{orderId}";
    private final String POST_ORDER = "";
    private final String DELETE_ORDER_DETAIL = "/detail/{orderDetailId}";
    private final String PATCH_ORDER = "";

    @ApiOperation(value = "결제된 주문 리스트 조회", notes = "")
    @GetMapping(GET_ORDER_LIST)
    public ResponseDto<List<GetOrderResponseDto>> getOrderList(
        @ApiParam(hidden = true)
        @AuthenticationPrincipal String userId,
        @PathVariable("storeId") int storeId,
        @PathVariable("orderState") String orderState
    ) {
        ResponseDto<List<GetOrderResponseDto>> response = orderService.getOrderList(userId, storeId, orderState);
        return response;
    }

    @ApiOperation(value = "주문번호에 해당하는 상세주문 리스트", notes = "")
    @GetMapping(GET_ORDER_DETAIL_LIST)
    public ResponseDto<List<GetOrderDetailResponseDto>> getOrderDetailList(
        @ApiParam(hidden = true)
        @AuthenticationPrincipal String userId,    
        @PathVariable("orderId") int orderId
    ) {
        ResponseDto<List<GetOrderDetailResponseDto>> response = orderService.getOrderDetailList(userId, orderId);
        return response;
    }

    @ApiOperation(value = "주문하기", notes = "")
    @PostMapping(POST_ORDER)
    public ResponseDto<PostOrderResponseDto> postOrder(@Valid @RequestBody PostOrderDto requestBody) {
        ResponseDto<PostOrderResponseDto> response = orderService.postOrder(requestBody);
        return response;
    }

    // todo : 필요한지 확인
    @ApiOperation(value = "장바구니 메뉴 삭제", notes = "Path Variable에 menuId를 포함하여 요청을 하고, 성공시 장바구니 전체 정보 반환, 실패시 실패 메세지를 반환")
    @DeleteMapping(DELETE_ORDER_DETAIL)
    public ResponseDto<DeleteOrderResponseDto> deleteOrderDetail(@ApiParam(value = "상세 주문 번호", example = "1", required = true) @PathVariable("orderDetailId") int orderDetailId) {
        ResponseDto<DeleteOrderResponseDto> response = orderService.deleteOrderDetail(orderDetailId);
        return response;
    }

    @ApiOperation(value = "주문 상태 변경", notes = "Request Header Athorization에 Bearer JWT를 포함하고 Request Body에 orderId, orderState를 포함하여 요청하면 성공 시 성공 메세지, 실패 시 실패 메세지를 반환")
    @PatchMapping(PATCH_ORDER)
    public ResponseDto<PatchOrderResponseDto> patchOrder(
        @ApiParam(hidden = true)
        @AuthenticationPrincipal String userId,
        @Valid @RequestBody PatchOrderDto requestBody) {
            ResponseDto<PatchOrderResponseDto> response = orderService.patchOrder(userId, requestBody);
            return response;
        }

    
}
