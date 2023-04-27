package com.kiosk.kioskback.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.category.PatchCategoryDto;
import com.kiosk.kioskback.dto.request.category.PostCategoryDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.category.DeleteCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.GetCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.PatchCategoryResponseDto;
import com.kiosk.kioskback.dto.response.category.PostCategoryResponseDto;
import com.kiosk.kioskback.entity.CategoryEntity;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.repository.CategoryRepository;
import com.kiosk.kioskback.repository.MenuRepository;
import com.kiosk.kioskback.repository.StoreRepository;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.CategoryService;

@Service
public class CategoryServiceImplements implements CategoryService {

    @Autowired CategoryRepository categoryRepository;
    @Autowired UserRepository userRepository;
    @Autowired StoreRepository storeRepository;
    @Autowired MenuRepository menuRepository;

    public ResponseDto<List<GetCategoryResponseDto>> getList(int storeId) {
        List<GetCategoryResponseDto> data = null;

        try {

            List<CategoryEntity> categoryEntityList = categoryRepository.findByStoreIdOrderByCategoryPriorityDesc(storeId);
            data = GetCategoryResponseDto.copyList(categoryEntityList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PostCategoryResponseDto> postCategory(String userId, PostCategoryDto dto) {
        PostCategoryResponseDto data = null;

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFailed("Not Exist User!");

            CategoryEntity categoryEntity = new CategoryEntity(dto);
            categoryRepository.save(categoryEntity);

            data = new PostCategoryResponseDto(categoryEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PatchCategoryResponseDto> patchCategory(String userId, PatchCategoryDto dto) {
        PatchCategoryResponseDto data = null;

        int categoryId = dto.getCategoryId();

        try {

            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
            if (categoryEntity == null) return ResponseDto.setFailed("Not Exist Category");
   
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (!userEntity.isAdmin()) return ResponseDto.setFailed("Do Not Admin!");         

            categoryEntity.patch(dto);
            categoryRepository.save(categoryEntity);

            data = new PatchCategoryResponseDto(categoryId, userId, categoryId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteCategoryResponseDto> deleteCategory(String userId, int categoryId) {
        DeleteCategoryResponseDto data = null;

        try {

            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
            if (categoryEntity == null) return ResponseDto.setFailed("Not Exist CategoryId");

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (!userEntity.isAdmin()) return ResponseDto.setFailed("Do Not Admin!");

            menuRepository.deleteByCategoryId(categoryId);

            categoryRepository.delete(categoryEntity);

            data = new DeleteCategoryResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    
    
}
