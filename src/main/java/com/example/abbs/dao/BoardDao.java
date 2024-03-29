package com.example.abbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.abbs.entity.Board;

@Mapper
public interface BoardDao {


	@Select("select b.*, u.uname from board b"
			+ " join users u on b.uid=u.uid"
			+ " where b.bid=#{bid}")
	Board getBoard(int bid);

	// ${} = 항목명(bid, content 등)이 들어감 - 문구 그대로 들어감
	// #{} = 내용이 들어감 (content 안의 내용 등) - String이면 ''을 붙여서, int면 그대로
	@Select("select count(b.bid) from board b"
	         + " JOIN users u ON b.uid=u.uid"
	         + " where b.isDeleted=0 and ${field} like #{query}")
	int getBoardCount(String field, String query);
	
	@Select("select b.*, u.uname from board b"
			+ " join users u on b.uid=u.uid"
			+ " where b.isDeleted=0 and ${field} like #{query}"
			+ " order by b.writeTime desc"
			+ " limit #{count} offset #{offset}")
	List<Board> getBoardList(String field, String query, int count, int offset);
	
	@Insert("insert into board values(default, #{uid}, #{title}, #{content}"
			+ ", default, default, default, default, default, #{files})")
	void insertBoard(Board board);
	
	@Update("update board set title=#{title}, content=#{content}, writeTime=now(), "
			+ "files=#{files} where bid=#{bid}")
	void updateBoard(Board board);
	
	@Update("update board set isDeleted=1 where bid=#{bid}")
	void deleteBoard(int bid);
	
	@Update("update board set ${field}=${field}+1 where bid=#{bid}")
	void increaseCount(String field, int bid);

	@Update("update board set likeCount=#{count} where bid=#{bid}")
	void updateLikeCount(int bid, int count);
}
