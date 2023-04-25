package com.kiosk.kioskback.service.implementation;

import java.util.List;

import com.kiosk.kioskback.dto.request.category.PatchCategoryDto;
import com.kiosk.kioskback.dto.request.category.PostCategoryDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.category.DeleteCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.GetCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.PatchCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.PostCategoryResponseDto;
import com.kiosk.kioskback.service.CategoryService;

public class CategoryServiceImplements implements CategoryService {

    @Override
    public ResponseDto<List<GetCategoryResponseDto>> getList(int storeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getList'");
    }

    @Override
    public ResponseDto<PostCategoryResponseDto> postCategory(String userId, PostCategoryDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postCategory'");
    }

    @Override
    public ResponseDto<PatchCategoryResponseDto> patchCategory(String userId, PatchCategoryDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchCategory'");
    }

    @Override
    public ResponseDto<DeleteCategoryResponseDto> deleteCategory(String userId, int menuCategoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
    }

    
    
}
