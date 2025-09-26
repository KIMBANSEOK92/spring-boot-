package com.example.test1.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.BoardMapper;
import com.example.test1.model.Board;

@Service
public class BoardService {

	@Autowired
	BoardMapper boardMapper;

	public HashMap<String, Object> boardInfo(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("service => " + map);
		Board board = boardMapper.boardInfo(map);
		if (board != null) {
			System.out.println(board.getBoardNo());
			System.out.println(board.getTitle());
			System.out.println(board.getUserId());
			System.out.println(board.getCnt());
		}
		resultMap.put("info", board);
		resultMap.put("result", "success");

		return resultMap;
	}

	public HashMap<String, Object> getBoardList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("service => " + map);
		List<Board> list = boardMapper.boardList(map);

		resultMap.put("list", list);
		resultMap.put("result", "success");

		return resultMap;
	}

	public HashMap<String, Object> removeBoard(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("service => " + map);
		int cnt = boardMapper.deleteBoard(map);
		
		resultMap.put("result", "success");

		return resultMap;
	}
	public HashMap<String, Object> addBoard(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("service => " + map);
		int cnt = boardMapper.insertBoard(map);
		
		resultMap.put("result", "success");

		return resultMap;
	}
}
