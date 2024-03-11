package com.example.abbs.service;

import java.util.List;

import com.example.abbs.entity.Board;

public interface BoardService {

	public static final int LIST_PER_PAGE  = 10; // 한 페이지당 글 목록의 개수
	public static final int PAGE_PER_SCREEN = 10; // 한 화면에 표시되는 페이지 개수
	
	Board getBoard(int bid);
	
	int getBoardCount(String field, String query);
	
	List<Board> getBoardList(int page, String field, String query);
	
	void insertBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoard(int bid);
	
	void increaseViewCount(int bid);
	
	void increaseReplyCount(int bid);
	
	void increaseLikeCount(int bid);
}
