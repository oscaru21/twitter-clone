package com.oumana.service;

import java.util.List;
import java.util.Set;

import com.oumana.entity.Tweet;
import com.oumana.payload.TweetResponse;

public interface TweetService {
	TweetResponse getTweets(Integer pageNo, Integer pageSize);
	Set<Tweet> getTweetsByUsername(String username);
	Tweet createTweet(String username, Tweet tweet);
	Tweet updateTweet(String username, Long id, Tweet tweet);
	void deleteTweet(Long id);
}
