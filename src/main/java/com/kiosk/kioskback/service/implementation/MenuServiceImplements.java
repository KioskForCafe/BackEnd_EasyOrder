package com.kiosk.kioskback.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.menu.PatchMenuDto;
import com.kiosk.kioskback.dto.request.menu.PostMenuDto;
import com.kiosk.kioskback.dto.response.MenuDto;
import com.kiosk.kioskback.dto.response.OptionsDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.menu.DeleteMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuDetailResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.PatchMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.PostMenuResponseDto;
import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.MenuRepository;
import com.kiosk.kioskback.repository.OptionRepository;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.MenuService;

@Service
public class MenuServiceImplements implements MenuService{

    @Autowired private MenuRepository menuRepository;
    @Autowired private OptionRepository optionRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public ResponseDto<List<GetMenuResponseDto>> getMenuInCategory(int storeId, String categoryName) {

        List<GetMenuResponseDto> data = null;

        try {
            List<MenuEntity> menuList = menuRepository.findByStoreIdAndCategoryName(storeId, categoryName);
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

            data = new GetMenuDetailResponseDto(menuEntity, optionList);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<PostMenuResponseDto> postMenu(String userId, PostMenuDto dto) {
        
        PostMenuResponseDto data = null;

        try {
            // 존재하는 유저인지 확인하는 메서드
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);

            // 메뉴 Entity에 데이터 추가
            MenuEntity menuEntity = new MenuEntity(dto);
            menuRepository.save(menuEntity);

            // 메뉴 id로 optionEntity 리스트 가져옴
            List<OptionEntity> optionEntityList = optionRepository.findByMenuId(dto.getMenuDto().getMenuId());
            // optionEntity 리스트를 optionsDto에 매칭
            List<OptionsDto> optionList = PostMenuResponseDto.copyList(optionEntityList);

            data = new PostMenuResponseDto(new MenuDto(menuEntity), optionList);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<PatchMenuResponseDto> patchMenu(String userId, PatchMenuDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchMenu'");
    }

    @Override
    public ResponseDto<DeleteMenuResponseDto> deleteMenu(String userId, int menuId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMenu'");
    }
    
}
