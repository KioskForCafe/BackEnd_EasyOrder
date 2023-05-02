package com.kiosk.kioskback.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.menu.PatchMenuDto;
import com.kiosk.kioskback.dto.request.menu.PostMenuDto;
import com.kiosk.kioskback.dto.response.MenuResponseDto;
import com.kiosk.kioskback.dto.response.OptionResponseDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.menu.DeleteMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuDetailResponseDto;
import com.kiosk.kioskback.dto.response.menu.GetMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.PatchMenuResponseDto;
import com.kiosk.kioskback.dto.response.menu.PostMenuResponseDto;
import com.kiosk.kioskback.entity.MenuEntity;
import com.kiosk.kioskback.entity.OptionEntity;
import com.kiosk.kioskback.entity.StoreEntity;
import com.kiosk.kioskback.entity.UserEntity;
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

            data = new GetMenuDetailResponseDto(menuEntity, optionList);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // todo : 메뉴 등록 후 리스트로 반환할 예정이었는데 List가 아님
    // todo : ResponseDto<List<PostMenuResponseDto>> postMenu(String userId, PostMenuDto dto)
    @Override
    public ResponseDto<PostMenuResponseDto> postMenu(String userId, PostMenuDto dto) {
        
        // todo : List<PostMenuResponseDto> data = null;
        PostMenuResponseDto data = null;

        try {
            // 존재하는 유저인지 확인하는 메서드
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);

            // todo : 로그인 유저가 관리자 인지 확인
            // todo : 관리자라면 해당 매장의 주인이 맞는지 확인

            // todo : 메뉴 등록에는 메뉴 id는 주지 않는다.
            // 메뉴 id로 optionEntity 리스트 가져옴
            List<OptionEntity> optionEntityList = optionRepository.findByMenuId(dto.getMenuDto().getMenuId());
            if(optionEntityList == null) return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

            // todo : MenuEntity menuEntity = new MenuResponseDto();
            // todo : menuEntity.toMenuEntity(dto);
            // 메뉴 Entity에 데이터 추가
            MenuEntity menuEntity = new MenuResponseDto().toMenuEntity(dto);
            menuRepository.save(menuEntity);

            // todo : 옵션 리스트 목록 저장
            // todo : OptionEntity optionEntity = new OptionEntity(dto); or PostMenuResponseDto.toOptionEntity(dto);
            // todo : 리스트 조회
            // todo : List<MenuEntity> menuList = menuRepository.findByStoreIdAndCategoryId(storeId, categoryId);
            // todo : data = PostMenuResponseDto.copyList(menuList);
            // optionEntity 리스트를 optionsDto에 매칭
            List<OptionResponseDto> optionList = PostMenuResponseDto.copyList(optionEntityList);

            // menuEntity와 optionList 데이터를 dto로 변환
            MenuResponseDto menuDto = new MenuResponseDto(menuEntity, optionList);

            data = new PostMenuResponseDto(menuDto);
            
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
            // 사용자가 가져온 정보에 해당하는 menuId를 가져옴
            int menuId = dto.getMenuDto().getMenuId();

            // todo : userId가 존재하는지
            // todo : 존재한다면 관리자권환이 있는지

            // menuId로 해당하는 menu의 정보를 불러옴
            MenuEntity menuEntity = menuRepository.findByMenuId(menuId);
            if(menuEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_MENU);

            // 사용자가 가져온 정보에 해당하는 storeId를 가져옴
            int storeId = dto.getMenuDto().getStoreId();
            // storeId에 해당하는 store 정보를 가져옴
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);
            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);

            // todo : isEqualsUserId = userId.equals(storeEntity.getUserId())
            // 가져온 store 정보와 사용자가 입력한 userId가 같은지 확인
            boolean hasPermission = storeEntity.getUserId() == userId;
            if(!hasPermission) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            // todo : option 리스트를 가져와서 dto로 변환하면 원래 있던 option 그대로 저장됨
            // todo : PatchMenuDto의 클라이언트에서 준 데이터를 저장해야함 (menu, optionList 둘다 수정된 데이터를 저장)
            // todo : 생각해보니 옵션 추가 및 옵션 삭제에 대한 기능을 추가로 만들어야 할 것 같다.

            // menuId에 해당하는 option 리스트를 가져옴
            List<OptionEntity> optionEntityList = optionRepository.findByMenuId(menuId);
            // entity를 dto로 변환
            List<OptionResponseDto> optionList = PatchMenuResponseDto.copyList(optionEntityList);
            // todo : 수정한 menuEntity를 넣어야함
            // menuEntity와 optionList를 menuDto로 변환
            MenuResponseDto menuDto = new MenuResponseDto(menuEntity, optionList);

            data = new PatchMenuResponseDto(menuDto);
            

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
            // todo : userId가 존재하는지
            // todo : userId가 Admin의 권한이 있는지
            
            // menuId로 menuEntity의 정보를 가져옴
            MenuEntity menuEntity = menuRepository.findByMenuId(menuId);
            if(menuEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_MENU);
            
            // menuId에 해당하는 storeId를 가져옴
            int storeId = menuEntity.getStoreId();

            // storeEntity에서 userId에 해당하는 store 정보 리스트를 가져옴
            StoreEntity storeEntity = storeRepository.findByStoreId(storeId);

            if(storeEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);

            // todo : isUserId -> isEqualsUserId
            // menuId에 해당하는 storeId와 일치하는 store 정보에 해당하는 userId와 사용자가 입력한 userId가 같은지 확인
            boolean isUserId = userId.equals(storeEntity.getUserId());
            if(!isUserId) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

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
