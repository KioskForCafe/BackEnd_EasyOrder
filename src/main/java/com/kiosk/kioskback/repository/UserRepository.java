package com.kiosk.kioskback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiosk.kioskback.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,String>{
    
}
