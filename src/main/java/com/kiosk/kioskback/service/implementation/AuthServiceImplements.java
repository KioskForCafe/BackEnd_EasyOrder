package com.kiosk.kioskback.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.auth.PostSignInDto;
import com.kiosk.kioskback.dto.request.auth.PostSignUpDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.auth.PostSignInResponseDto;
import com.kiosk.kioskback.dto.response.auth.PostSignUpResponseDto;
import com.kiosk.kioskback.entity.UserEntity;
import com.kiosk.kioskback.provider.TokenProvider;
import com.kiosk.kioskback.repository.UserRepository;
import com.kiosk.kioskback.service.AuthService;

import io.swagger.models.Response;



@Service
public class AuthServiceImplements implements AuthService {
    
    @Autowired private TokenProvider tokenProvider;
    @Autowired private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseDto<PostSignUpResponseDto> postSignUp(PostSignUpDto dto) {

        PostSignUpResponseDto data = null;

        String userId = dto.getUserId();
        String userName = dto.getUserName();
        String password = dto.getPassword();
        String userEmail = dto.getUserEmail();
        String telNumber = dto.getTelNumber();
        
        try {
            boolean hasUserId = userRepository.existsByUserId(userId);
            if (hasUserId) return ResponseDto.setFailed(ResponseMessage.EXIST_USER_ID);

            boolean hasUserName = userRepository.existsByUserName(userName);
            if (hasUserName) return ResponseDto.setFailed(ResponseMessage.EXIST_USER_NAME);

            boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
            if (hasTelNumber) return ResponseDto.setFailed(ResponseMessage.EXIST_USER_TEL_NUMBER);

            boolean hasUserEmail = userRepository.existsByUserEmail(userEmail);
            if (hasUserEmail) return ResponseDto.setFailed(ResponseMessage.EXIST_USER_EMAIL);


            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            data = new PostSignUpResponseDto(true);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PostSignInResponseDto> postSignIn(PostSignInDto dto) {

        PostSignInResponseDto data = null;

        String userId = dto.getUserId();
        String password = dto.getPassword();

        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByUserId(userId);

            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);

            boolean isEqualPassword = passwordEncoder.matches(password, userEntity.getPassword());
            if (!isEqualPassword) return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        try {
            String token = tokenProvider.create(userId);
            data = new PostSignInResponseDto(userEntity, token);
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
