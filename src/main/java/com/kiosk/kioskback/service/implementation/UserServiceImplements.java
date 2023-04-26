package com.kiosk.kioskback.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.user.PostCheckEmailDuplicateDto;
import com.kiosk.kioskback.dto.request.user.PostCheckIdDuplicateDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckEmailDuplicateResponseDto;
import com.kiosk.kioskback.dto.response.user.PostCheckIdDuplicateResponseDto;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.UserServcie;

@Service
public class UserServiceImplements implements UserServcie {
    
    @Autowired private UserRepository userRepository;

    public ResponseDto<PostCheckEmailDuplicateResponseDto> postCheckEmailDuplicate(PostCheckEmailDuplicateDto dto) {  
        PostCheckEmailDuplicateResponseDto data =null;

        String userEmail = dto.getUserEmail();

        try {
            boolean hasUserEmail = userRepository.existsByUserEmail(userEmail);
            data = new PostCheckEmailDuplicateResponseDto(!hasUserEmail);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PostCheckIdDuplicateResponseDto> postCheckIdDuplicate(PostCheckIdDuplicateDto dto) {
        PostCheckIdDuplicateResponseDto data = null;

        String userId = dto.getUserId();

        try {
            boolean hasUserId = userRepository.existsByUserId(userId);
            data = new PostCheckIdDuplicateResponseDto(!hasUserId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }


}
