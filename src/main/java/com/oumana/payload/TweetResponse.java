package com.oumana.payload;

import java.util.List;

import com.oumana.entity.Tweet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetResponse {
	private List<Tweet> content;
	private Integer pageNo;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private boolean last;
}
