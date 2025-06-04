package com.mb.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.userservice.model.entity.DependentEntity;


@Repository
public interface DependentRepository extends JpaRepository<DependentEntity, Long> {
    
}