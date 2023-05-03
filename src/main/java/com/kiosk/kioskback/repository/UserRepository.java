package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiosk.kioskback.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
   
    public boolean existsByUserEmail(String userEmail);
    public boolean existsByUserId(String userId);
    public boolean existsByTelNumber(String telNumber);
    public boolean existsByUserName(String userName);

    public boolean existsByUserEmailOrUserIdOrTelNumberOrUserName(String userEmail, String userId, String telNumber, String userName);

    public UserEntity findByUserId(String userId);

}
