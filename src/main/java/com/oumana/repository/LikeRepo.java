package com.oumana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oumana.entity.Heart;

public interface LikeRepo extends JpaRepository<Heart, Long>{

}
