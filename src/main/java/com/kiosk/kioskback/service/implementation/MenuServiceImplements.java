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
        int categoryId = dto.getCategoryId();
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

        try {
            // 존재하는 유저인지 확인하는 메서드
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            if(!userEntity.isAdmin()) return ResponseDto.setFailed(ResponseMessage.NOT_ADMIN);

            // 사용자가 가져온 정보에 해당하는 menuId를 가져옴
            int menuId = dto.getMenuId();

            // menuId로 해당하는 menu의 정보를 불러옴
            MenuEntity menuEntity = menuRepository.findByMenuId(menuId);
            if(menuEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_MENU);

            // 사용자가 가져온 정보에 해당하는 storeId를 가져옴
            int storeId = menuEntity.getStoreId();
            // storeId에 해당하는 store 정보를 가져옴
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            // 가져온 store 정보와 사용자가 입력한 userId가 같은지 확인
            boolean hasPermission = userId.equals(storeEntity.getUserId());
            if(!hasPermission) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            // 메뉴정보 수정 및 저장
            menuEntity.patch(dto);
            menuRepository.save(menuEntity);

            // menuId에 해당하는 option 리스트 수정 및 추가 저장
            // todo: SQL문을 살짝 손보는 것으로 해결될 수 있음
            List<OptionEntity> optionEntityList = optionRepository.findByMenuId(menuId);
            List<PatchMenuOptionDto> patchMenuOptionDtoList = dto.getOptionList();

            List<OptionEntity> patchedOptionEntityList = new ArrayList<>();

            for(OptionEntity optionEntity : optionEntityList){
                for(PatchMenuOptionDto patchMenuOptionDto : patchMenuOptionDtoList){
                    if(optionEntity.getOptionId() == patchMenuOptionDto.getOptionId()){
                        optionEntity.patch(patchMenuOptionDto);
                    }
                    if(patchMenuOptionDto.getOptionId() == null){
                        optionEntity = new OptionEntity(patchMenuOptionDto, menuId);
                    }
                    patchedOptionEntityList.add(optionEntity);
                }
            }

            optionRepository.saveAll(patchedOptionEntityList);
            
            optionEntityList = optionRepository.findByMenuId(menuId);
            int categoryId = menuEntity.getCategoryId();
            String categoryName =null;
            if(categoryId>0){
                CategoryEntity categoryEntity = categoryRepository.findByCategoryId(menuEntity.getCategoryId());
                categoryName = categoryEntity.getCategoryName();
            }

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
