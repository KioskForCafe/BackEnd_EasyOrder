package com.kiosk.kioskback.service;

import java.util.List;

import com.kiosk.kioskback.dto.request.menu.PatchMenuDto;
import com.kiosk.kioskback.dto.request.menu.PostMenuDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.menu.DeleteMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuDetailResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuInCategoryResponseDto;
import com.kiosk.kioskback.dto.response.menu.PatchMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.PostMenuResponseDto;

public interface MenuService {
    public ResponseDto<List<GetMenuInCategoryResponseDto>> getMenuInCategory(int storeId, String categoryName);
    public ResponseDto<GetMenuDetailResponseDto> getMenuDetail(int menuId);

    public ResponseDto<PostMenuResponseDto> postMenu(String userId, PostMenuDto dto);

    public ResponseDto<PatchMenuResponseDto> patchMenu(String userId, PatchMenuDto dto);

    public ResponseDto<DeleteMenuResponseDto> deleteMenu(String userId, int menuId);

}