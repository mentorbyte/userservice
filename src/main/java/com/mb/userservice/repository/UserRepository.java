package com.mb.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.userservice.model.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}