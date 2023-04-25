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
import com.kiosk.kioskback.dto.request.category.PatchCategoryDto;
import com.kiosk.kioskback.dto.request.category.PostCategoryDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.category.DeleteCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.GetCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.PatchCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.PostCategoryResponseDto;
import com.kiosk.kioskback.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;

@Api(description = "카테고리 모듈")
@RestController
@RequestMapping(ApiPattern.CATEGORY)
public class CategoryController {

    @Autowired private CategoryService categoryService;

    private final String GET_LIST = "/list/{storeId}";
    private final String POST_CATEGORY = "";
    private final String PATCH_CATEGORY = "";
    private final String DELETE_CATEGORY = "/{categoryId}";

    @ApiOperation(value = "카테고리 리스트 조회", notes = "Path Variable에 storeId를 포함하여 요청을 하면, 성공시 게시물 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_LIST)
    public ResponseDto<List<GetCategoryResponseDto>> getList(@ApiParam(value = "매장 식별 번호", example = "1", required = true) @PathVariable("storeId") int storeId) {
        ResponseDto<List<GetCategoryResponseDto>> response = categoryService.getList(storeId);

        return response;
    }

    @ApiOperation(value = "카테고리 등록", notes = "카테고리 이름, 우선순위를 등록하여 성공시 작성된 카테고리 정보를  반환, 실패시 실패 메세지를 반환")
    @PostMapping(POST_CATEGORY)
    public ResponseDto<PostCategoryResponseDto> postCategory(@ApiParam(hidden = true) @AuthenticationPrincipal String userId, @Valid @RequestBody PostCategoryDto requestBody) {
        ResponseDto<PostCategoryResponseDto> response = categoryService.postCategory(userId, requestBody);

        return response;
    }

    @ApiOperation(value = "카테고리 수정", notes = "Request Header Athorization에 Bearer JWT를 포함하고Request Body에 categoryId, categoryPriority를 포함하여 요청을 하면, 성공시 카테고리 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @PatchMapping(PATCH_CATEGORY)
    public ResponseDto<PatchCategoryResponseDto> patchCategory(@AuthenticationPrincipal String userId, @Valid @RequestBody PatchCategoryDto requestBody) {
        ResponseDto<PatchCategoryResponseDto> response = categoryService.patchCategory(userId, requestBody);

        return response;
    }

    @ApiOperation(value = "카테고리 삭제", notes = "Request Header Athorization에 Bearer JWT를 포함하고 Path Variable에 categoryId를 포함하여 요청을 하면, 성공시 true를 반환, 실패시 실패 메세지를 반환")
    @DeleteMapping(DELETE_CATEGORY)
    public ResponseDto<DeleteCategoryResponseDto> deleteCategory(@ApiParam(hidden = true) @AuthenticationPrincipal String userId, @ApiParam(value = "카테고리 식별 번호", example = "1", required = true) @PathVariable("categoryId") int categoryId) {
        ResponseDto<DeleteCategoryResponseDto> response = categoryService.deleteCategory(userId, categoryId);

        return response;
    }
    
}
