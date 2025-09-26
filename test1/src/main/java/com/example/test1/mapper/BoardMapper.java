package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Board;

@Mapper
public interface BoardMapper {

	Board boardInfo(HashMap<String, Object> map);
	
	// 게시글 목록을 호출한다.↓
	List<Board> boardList(HashMap<String, Object> map); // 두개이상의 값을 리턴할 때는 List<>로 묶어줘야한다.
	
	// 게시글 삭제
	int deleteBoard(HashMap<String, Object> map);
	
	// 게시글 목록
	int insertBoard(HashMap<String, Object> map);
}
