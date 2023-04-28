package com.kiosk.kioskback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiosk.kioskback.common.constants.ApiPattern;
import com.kiosk.kioskback.dto.request.order.PostOrderDetailDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.order.PostOrderDetailResponseDto;
import com.kiosk.kioskback.service.OrderDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;

@Api(description = "장바구니 모듈")
@RestController
@RequestMapping(ApiPattern.ORDER_DETAIL)
public class OrderDetailController {

    @Autowired private OrderDetailService orderDetailService;

    private final String POST_ORDER_DETAIL = "";

    @ApiOperation(value = "장바구니 메뉴 추가", notes = "Request Body에 상품 이름, 상품 가격, 상품 갯수, 부가 옵션, 부가 옵션 가격을 입력하여 메뉴를 추가하고, 성공 시에는 메뉴 추가 성공 여부에 따라 true/false가 반환됨")
    @PostMapping(POST_ORDER_DETAIL)
    public ResponseDto<PostOrderDetailResponseDto> postOrderDetail(@ApiParam(hidden = true) @Valid @RequestBody PostOrderDetailDto requestBody) {

        ResponseDto<PostOrderDetailResponseDto> response = orderDetailService.postOrderDetail(requestBody);

        return response;
    }
    
}