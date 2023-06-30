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
import com.kiosk.kioskback.dto.request.menu.PatchMenuDto;
import com.kiosk.kioskback.dto.request.menu.PostMenuDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.menu.DeleteMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuDetailResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.PatchMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.PostMenuResponseDto;
import com.kiosk.kioskback.service.MenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// @Tag(description = "메뉴 모듈")
@RestController
@RequestMapping(ApiPattern.MENU)
public class MenuController {

    private static final String GET_MENU_LIST = "/list/{storeId}/{categoryId}";
    private static final String GET_MENU_DETAIL = "/{menuId}";

    private static final String POST_MENU = "";

    private static final String PATCH_MENU = "";

    private static final String DELETE_MENU = "/{menuId}";
    
    @Autowired private MenuService menuService;
    
    @Operation(summary = "판매 상품 리스트 조회", description = "Path Variable에 매장 번호와 카테고리명을 포함하여 요청하면, 성공 시 그 카테고리의 상품(리스트) 데이터를 반환, 실패 시 실패 메세지를 반환")
    @GetMapping(GET_MENU_LIST)
    public ResponseDto<List<GetMenuResponseDto>> getMenuInCategory(
        @Parameter(description = "가게 번호", example = "1", required = true)
        @PathVariable("storeId") int storeId,
        @Parameter(description = "카테고리번호", example = "1", required = true)
        @PathVariable("categoryId") int categoryId
        ) {
            ResponseDto<List<GetMenuResponseDto>> response = menuService.getMenuInCategory(storeId, categoryId);

            return response;
    }

    @Operation(summary = "메뉴 상세 정보 조회", description = "Path Variable에 상품 번호를 포함하여 요청하면, 성공 시 해당하는 해당하는 상품의 상세 데이터를 반환, 실패 시 실패 메세지를 반환")
    @GetMapping(GET_MENU_DETAIL)
    public ResponseDto<GetMenuDetailResponseDto> getMenuDetail(
        @Parameter(description = "메뉴 번호", example = "1", required = true)
        @PathVariable("menuId") int menuId
        ) {
            ResponseDto<GetMenuDetailResponseDto> response = menuService.getMenuDetail(menuId);

            return response;
    }

    @Operation(summary = "메뉴 등록", description = "Request Header Athorization에 Bearer JWT를 포함하고, Request Body에 storeId, menuCategoryId, menuName, menuPrice, menuState, menuImg, optionsName, optionsPrice를 포함하여 전송하면 판매상품 작성 결과로 작성된 상품 정보를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(POST_MENU)
    public ResponseDto<PostMenuResponseDto> postMenu(
        @Parameter(hidden = true)
        @AuthenticationPrincipal String userId, 
        @Valid @RequestBody PostMenuDto requestBody
        ) {
            ResponseDto<PostMenuResponseDto> response = menuService.postMenu(userId, requestBody);
            return response;
    }

    @Operation(summary = "메뉴 정보 수정", description = "Request Header Athorization에 Bearer JWT를 포함하고 Request Body에 menuName, menuPrice, menuState, menuImg, optionsName, optionsPrice를 포함하여 요청을 하면, 성공시 판매상품 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @PatchMapping(PATCH_MENU)
    public ResponseDto<PatchMenuResponseDto> patchMenu(
        @Parameter(hidden = true)
        @AuthenticationPrincipal String userId, 
        @Valid @RequestBody PatchMenuDto requestBody
        ) {
            ResponseDto<PatchMenuResponseDto> response = menuService.patchMenu(userId, requestBody);
            return response;
    }

    @Operation(summary = "특정 메뉴 삭제", description = "Request Header Athorization에 Bearer JWT를 포함하고 Path Variable에 menuId를 포함하여 요청을 하면, 성공시 true를 반환, 실패시 실패 메세지를 반환"					)
    @DeleteMapping(DELETE_MENU)
    public ResponseDto<DeleteMenuResponseDto> deleteMenu(
        @Parameter(hidden = true)
        @AuthenticationPrincipal String userId,
        @Parameter(description = "메뉴 번호", example = "1", required = true)
        @PathVariable("menuId") int menuId) {
            ResponseDto<DeleteMenuResponseDto> response = menuService.deleteMenu(userId, menuId);
            return response;
    }

}
