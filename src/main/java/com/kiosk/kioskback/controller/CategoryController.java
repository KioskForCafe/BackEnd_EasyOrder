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
import com.kiosk.kioskback.dto.response.category.GetListResponseDto;
import com.kiosk.kioskback.dto.response.category.PatchCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.PostCategoryResponseDto;
import com.kiosk.kioskback.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiPattern.CATEGORY)
public class CategoryController {

    @Autowired private CategoryService categoryService;

    private final String GET_LIST = "/list";
    private final String POST_CATEGORY = "";
    private final String PATCH_CATEGORY = "";
    private final String DELETE_CATEGORY = "/{menuCategoryId}";

    @GetMapping(GET_LIST)
    public ResponseDto<List<GetListResponseDto>> getList() {
        ResponseDto<List<GetListResponseDto>> response = categoryService.getList();

        return response;
    }

    @PostMapping(POST_CATEGORY)
    public ResponseDto<PostCategoryResponseDto> postCategory(@AuthenticationPrincipal String userId, @Valid @RequestBody PostCategoryDto requestBody) {
        ResponseDto<PostCategoryResponseDto> response = categoryService.postCategory(userId, requestBody);

        return response;
    }

    @PatchMapping(PATCH_CATEGORY)
    public ResponseDto<PatchCategoryResponseDto> patchCategory(@AuthenticationPrincipal String userId, @Valid @RequestBody PatchCategoryDto requestBody) {
        ResponseDto<PatchCategoryResponseDto> response = categoryService.patchCategory(userId, requestBody);

        return response;
    }

    @DeleteMapping(DELETE_CATEGORY)
    public ResponseDto<DeleteCategoryResponseDto> deleteCategory(@AuthenticationPrincipal String userId, @PathVariable("menuCategoryId") int menuCategoryId) {
        ResponseDto<DeleteCategoryResponseDto> response = categoryService.deleteCategory(userId, menuCategoryId);

        return response;
    }
    
}
