package com.kiosk.kioskback.entity;

import com.kiosk.kioskback.dto.request.auth.PostSignUpDto;
import com.kiosk.kioskback.dto.request.user.PatchUserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="User")
@Table(name="User")
public class UserEntity {
    @Id
    private String userId;
    private String userName;
    private String password;
    private String userEmail;
    private String userJoinDate;
    private boolean isAdmin;
    private String telNumber;
    
    public UserEntity(PostSignUpDto dto) {
        this.userId = dto.getUserId();
        this.userName = dto.getUserName();
        this.password = dto.getPassword();
        this.userEmail = dto.getUserEmail();
        this.telNumber = dto.getTelNumber();
        this.isAdmin = dto.isAdmin();
    }

    public void patch(PatchUserDto dto) {
        this.userId = dto.getUserId();
        this.userName = dto.getUserName();
        this.userEmail = dto.getUserEmail();
        this.telNumber = dto.getTelNumber();
    }
}
 