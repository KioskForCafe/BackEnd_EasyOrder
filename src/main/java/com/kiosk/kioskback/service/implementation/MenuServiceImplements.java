package com.kiosk.kioskback.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.menu.PatchMenuDto;
import com.kiosk.kioskback.dto.request.menu.PatchMenuOptionDto;
import com.kiosk.kioskback.dto.request.menu.PostMenuDto;
import com.kiosk.kioskback.dto.request.menu.PostMenuOptionDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.menu.DeleteMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuDetailResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.PatchMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.PostMenuResponseDto;
import com.kiosk.kioskback.entity.CategoryEntity;
import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.CategoryRepository;
import com.kiosk.kioskback.repository.MenuRepository;
import com.kiosk.kioskback.repository.OptionRepository;
import com.kiosk.kioskback.repository.StoreRepository;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.MenuService;

@Service
public class MenuServiceImplements implements MenuService{

    @Autowired private MenuRepository menuRepository;
    @Autowired private OptionRepository optionRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private StoreRepository storeRepository;
    @Autowired private CategoryRepository categoryRepository;

    @Override
    public ResponseDto<List<GetMenuResponseDto>> getMenuInCategory(int storeId, int categoryId) {

        List<GetMenuResponseDto> data = null;

        try {
            List<MenuEntity> menuList = menuRepository.findByStoreIdAndCategoryId(storeId, categoryId);
            data = GetMenuResponseDto.copyList(menuList);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<GetMenuDetailResponseDto> getMenuDetail(int menuId) {

        GetMenuDetailResponseDto data = null;

        try {
            MenuEntity menuEntity = menuRepository.findByMenuId(menuId); // menuId에 해당하는 menuEntity 정보 가져옴
            if(menuEntity == null) return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
            List<OptionEntity> optionList = optionRepository.findByMenuId(menuId); // menuId에 해당하는 option List를 가져옴
            // todo: 이렇게 해야하는게 맞는지 생각해봐야할 부분인것 같음
                int categoryId = menuEntity.getCategoryId();
                String categoryName =null;
                if(categoryId>0){
                    CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
                    categoryName = categoryEntity.getCategoryName();
                }

            data = new GetMenuDetailResponseDto(menuEntity, optionList, categoryName);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<PostMenuResponseDto> postMenu(String userId, PostMenuDto dto) {
        
        PostMenuResponseDto data = null;

        int storeId = dto.getStoreId();
        List<PostMenuOptionDto> optionList = dto.getOptionList();
        

        try {
            // 존재하는 유저인지 확인하는 메서드
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE_ID);

            boolean isEqualUserId = storeEntity.getUserId().equals(userId);
            if(!isEqualUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            MenuEntity menuEntity = PostMenuResponseDto.toMenuEntity(dto);
            menuEntity = menuRepository.save(menuEntity);
            int menuId = menuEntity.getMenuId();

            for(PostMenuOptionDto option : optionList){
                OptionEntity optionEntity = new OptionEntity(option, menuId);
                optionRepository.save(optionEntity);
            }

            data = new PostMenuResponseDto(true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<PatchMenuResponseDto> patchMenu(String userId, PatchMenuDto dto) {
        PatchMenuResponseDto data = null;

        int menuId = dto.getMenuId();
        int storeId = dto.getStoreId();
        Integer categoryId = dto.getCategoryId();
        String categoryName = null;
        List<PatchMenuOptionDto> patchMenuOptionDtoList = dto.getOptionList();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            MenuEntity menuEntity = menuRepository.findByMenuId(menuId);
            if(menuEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_MENU);

            boolean hasPermission = storeRepository.existsByUserIdAndStoreId(userId, storeId);
            if(!hasPermission) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            menuEntity.patch(dto);
            menuEntity = menuRepository.save(menuEntity);
            List<Integer> optionIdList = new ArrayList<Integer>();
            for(PatchMenuOptionDto patchMenuOptionDto : patchMenuOptionDtoList){
                Integer optionId = patchMenuOptionDto.getOptionId();
                optionIdList.add(optionId);
            }

            List<OptionEntity> deleteOptionEntityList = optionRepository.findOptionNotInList(optionIdList);
            
            optionRepository.deleteAll(deleteOptionEntityList);
            
            List<OptionEntity> patchedOptionEntityList = OptionEntity.copyList(patchMenuOptionDtoList, menuId);
            List<OptionEntity> optionEntityList =  optionRepository.saveAll(patchedOptionEntityList);
            
            if(categoryId != null){
                CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
                if(categoryEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CATEGORY_ID);
                categoryName = categoryEntity.getCategoryName();
            }

            System.out.println(menuEntity.getMenuId());
            System.out.println(menuEntity.getMenuName());
            System.out.println(menuEntity.getMenuPrice());
            System.out.println(menuEntity.isMenuState());
            
            data = new PatchMenuResponseDto(menuEntity, optionEntityList, categoryName);
            

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<DeleteMenuResponseDto> deleteMenu(String userId, int menuId) {
        DeleteMenuResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);
            
            // menuId로 menuEntity의 정보를 가져옴
            MenuEntity menuEntity = menuRepository.findByMenuId(menuId);
            if(menuEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_MENU);
            
            // menuId에 해당하는 storeId를 가져옴
            int storeId = menuEntity.getStoreId();

            // storeEntity에서 userId에 해당하는 store 정보 리스트를 가져옴
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);

            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);

            // menuId에 해당하는 storeId와 일치하는 store 정보에 해당하는 userId와 사용자가 입력한 userId가 같은지 확인
            boolean isEqualsUserId = userId.equals(storeEntity.getUserId());
            if(!isEqualsUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            // 같다면 해당하는 menu를 삭제함
            menuRepository.delete(menuEntity);
            data = new DeleteMenuResponseDto(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
