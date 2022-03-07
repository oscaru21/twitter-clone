package com.oumana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oumana.entity.Tweet;

public interface TweetRepo extends JpaRepository<Tweet, Long>{

}
