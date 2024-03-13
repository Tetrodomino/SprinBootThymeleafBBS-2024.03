package com.example.abbs.service;

import java.util.List;

import com.example.abbs.entity.Like;

public interface LikeService {

	Like getLike(int bid, String uid);

	Like getLikeByLid(int lid);
	
	List<Like> getLikeList(int bid);
	
	void insertLike(Like like);
	
	int toggleLike(Like like);
	
	int getLikeCount(int bid);
	
}
