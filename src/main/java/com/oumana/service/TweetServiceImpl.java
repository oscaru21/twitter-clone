package com.oumana.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oumana.entity.Tweet;
import com.oumana.entity.User;
import com.oumana.exceptions.ResourceNotFoundException;
import com.oumana.payload.TweetResponse;
import com.oumana.repository.TweetRepo;
@Service
public class TweetServiceImpl implements TweetService{
	@Autowired
	TweetRepo tweetRepo;
	
	@Autowired
	UserServiceImpl userService;
	
	@Override
	public TweetResponse getTweets(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Tweet> tweets = tweetRepo.findAll(pageable);
		//map tweet response
		TweetResponse tweetResponse = new TweetResponse();
		tweetResponse.setContent(tweets.getContent());
		tweetResponse.setPageNo(tweets.getNumber());
		tweetResponse.setPageSize(tweets.getSize());
		tweetResponse.setTotalElements(tweets.getTotalElements());
		tweetResponse.setTotalPages(tweets.getTotalPages());
		tweetResponse.setLast(tweets.isLast());
		return tweetResponse;
	}

	@Override
	public List<Tweet> getTweetsByUsername(String username) {
		User user = userService.getUserByUsername(username);
		return user.getTweets();
	}

	@Override
	public Tweet createTweet(String username, Tweet tweet) {
		User user = userService.getUserByUsername(username);
		tweet.setUser(user);
		tweet.setCreationDate(LocalDate.now());
		return tweetRepo.save(tweet);
	}

	@Override
	public Tweet updateTweet(String username, Long id, Tweet tweet) {
		User user = userService.getUserByUsername(username);
		Tweet tweetResult = tweetRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", id.toString()));
		tweetResult.setContent(tweet.getContent());
		return tweetRepo.save(tweetResult);
	}

	@Override
	public void deleteTweet(Long id) {
		tweetRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", id.toString()));
		tweetRepo.deleteById(id);
	}

}
