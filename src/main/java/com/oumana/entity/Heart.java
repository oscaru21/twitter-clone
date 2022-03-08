package com.oumana.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"user", "tweet"})
@Table(name = "hearts")
public class Heart {
	@Id
	@Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate creationDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_tweet_id", nullable = false)
	private Tweet tweet;
}
