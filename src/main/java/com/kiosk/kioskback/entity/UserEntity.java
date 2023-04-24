package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userJoinDate;
    private boolean isAdmin;
    private String telNumber;
    
}
