package com.oumana.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oumana.entity.Tweet;
import com.oumana.payload.TweetResponse;
import com.oumana.service.TweetServiceImpl;
import com.oumana.util.AppConstants;

@RestController
@RequestMapping("/users")
public class TweetController {
	@Autowired
	TweetServiceImpl tweetService;
	
	@GetMapping("/tweets")
	public ResponseEntity<TweetResponse> getTweet(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortingBy", defaultValue = AppConstants.DEFAULT_SORTING_FIELD, required = false) String sortingBy
			) {
		return ResponseEntity.ok().body(tweetService.getTweets(pageNo, pageSize));
	}
	
	@GetMapping("/{username}/tweets")
	public ResponseEntity<List<Tweet>> getTweetsByUsername(
			@PathVariable(name = "username") String username){
		return ResponseEntity.ok().body(tweetService.getTweetsByUsername(username));
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/{username}/tweets")
	public ResponseEntity<Tweet> createTweet(
			@PathVariable(name = "username") String username,
			@RequestBody Tweet tweet){
		URI uRI = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/" + username + "/tweets").toUriString());
		return ResponseEntity.created(uRI).body(tweetService.createTweet(username, tweet));
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{username}/tweets/{id}")
	public ResponseEntity<Tweet> updateTweet(
			@PathVariable(name = "username") String username,
			@PathVariable(name = "id") Long id,
			@RequestBody Tweet tweet){
		return ResponseEntity.ok().body(tweetService.updateTweet(username, id, tweet));
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{username}/tweets/{id}")
	public void deleteTweet(
			@PathVariable Long id,
			@PathVariable String username){
		tweetService.deleteTweet(id);
	}
}
