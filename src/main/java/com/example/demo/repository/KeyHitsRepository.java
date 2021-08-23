package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.KeyHitsEntity;

@Repository
public interface KeyHitsRepository extends JpaRepository<KeyHitsEntity, String>{

}
