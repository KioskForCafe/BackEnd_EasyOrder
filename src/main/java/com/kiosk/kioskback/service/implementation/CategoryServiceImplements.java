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
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.CategoryRepository;
import com.kiosk.kioskback.repository.StoreRepository;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.CategoryService;

@Service
public class CategoryServiceImplements implements CategoryService {

    @Autowired private CategoryRepository categoryRepository;
    @Autowired private StoreRepository storeRepository;
    @Autowired private UserRepository userRepository;

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

        int storeId = dto.getStoreId();

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);
            
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE_ID);

            boolean isEqualUserId = storeEntity.getUserId().equals(userId);
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            CategoryEntity categoryEntity = new CategoryEntity(dto);
            categoryRepository.save(categoryEntity);

            data = new PostCategoryResponseDto(true);

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
            if (categoryEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CATEGORY_ID);
   
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if (!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            int storeId = categoryEntity.getStoreId();
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE_ID);

            boolean isEqualUserId = storeEntity.getUserId().equals(userId);
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            categoryEntity.patch(dto);
            categoryRepository.save(categoryEntity);

            data = new PatchCategoryResponseDto(categoryEntity);

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
            if (categoryEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CATEGORY_ID);

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if (!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            int storeId = categoryEntity.getStoreId();
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE_ID);

            boolean isEqualUserId = storeEntity.getUserId().equals(userId);
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            categoryRepository.delete(categoryEntity);

            data = new DeleteCategoryResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    
    
}
