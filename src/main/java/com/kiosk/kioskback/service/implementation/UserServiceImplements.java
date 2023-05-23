package com.kiosk.kioskback.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.user.PatchUserDto;
import com.kiosk.kioskback.dto.request.user.PostCheckEmailDuplicateDto;
import com.kiosk.kioskback.dto.request.user.PostCheckIdDuplicateDto;
import com.kiosk.kioskback.dto.request.user.PostCheckTelNumberDuplicateDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.user.DeleteUserResponseDto;
import com.kiosk.kioskback.dto.response.user.GetUserResponseDto;
import com.kiosk.kioskback.dto.response.user.PatchUserResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckEmailDuplicateResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckIdDuplicateResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckTelNumberDuplicateResponseDto;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.UserServcie;

@Service
public class UserServiceImplements implements UserServcie {
    
    @Autowired private UserRepository userRepository;

    //^ 이메일 중복체크하기
    public ResponseDto<PostCheckEmailDuplicateResponseDto> postCheckEmailDuplicate(PostCheckEmailDuplicateDto dto) {  
        PostCheckEmailDuplicateResponseDto data =null;

        String userEmail = dto.getUserEmail();

        try {
            boolean hasUserEmail = userRepository.existsByUserEmail(userEmail);
            data = new PostCheckEmailDuplicateResponseDto(!hasUserEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //^ 아이디 중복체크하기
    public ResponseDto<PostCheckIdDuplicateResponseDto> postCheckIdDuplicate(PostCheckIdDuplicateDto dto) {
        PostCheckIdDuplicateResponseDto data = null;

        String userId = dto.getUserId();

        try {
            boolean hasUserId = userRepository.existsByUserId(userId);
            data = new PostCheckIdDuplicateResponseDto(!hasUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //^ 전화번호 중복체크하기
    public ResponseDto<PostCheckTelNumberDuplicateResponseDto> postCheckTelNumberDuplicate(PostCheckTelNumberDuplicateDto dto) {
        PostCheckTelNumberDuplicateResponseDto data = null;

        String telNumber = dto.getTelNumber();

        try {
            boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
            data = new PostCheckTelNumberDuplicateResponseDto(!hasTelNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //^ 유저 정보 조회
    public ResponseDto<GetUserResponseDto> getUser(String userId) {

        GetUserResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);

            data = new GetUserResponseDto(userEntity);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    //^ 유저 정보 수정
    public ResponseDto<PatchUserResponseDto> patchUser(String userId, PatchUserDto dto) {

        PatchUserResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);

            // todo : Email, TelNumber 중복 확인

            userEntity.patch(dto);
            userRepository.save(userEntity);

            data = new PatchUserResponseDto(userEntity);

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    //^ 유저 정보 삭제
    public ResponseDto<DeleteUserResponseDto> deleteUser(String userId) {
        DeleteUserResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);

            // todo : 관리자일 경우 매장을 모두 삭제해야 삭제가 가능함.
            // todo : 매장이 있는지 확인 후 있다면 Failed 없다면 삭제 성공
            userRepository.deleteById(userEntity.getUserId());

            data = new DeleteUserResponseDto(true);

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}
