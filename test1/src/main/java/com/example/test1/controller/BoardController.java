package com.example.test1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.BoardService;
import com.google.gson.Gson;

@Controller // @Controller를 꼭 만들어줘야 정상 페이지를 작동 할 수 있다.
public class BoardController {

	@Autowired
	BoardService boardService;

	@RequestMapping("/board-list.do") // @는 spring에서는 아주 중요한 역할을 한다. 자바에서는 오버라이딩을 한다. 주소를 만들어준다.
	public String login(Model model) throws Exception {

		return "/board-list";
	}

	@RequestMapping(value = "/board-info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map);
		resultMap = boardService.boardInfo(map);

		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/board-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public String boardList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = boardService.getBoardList(map);

		return new Gson().toJson(resultMap);
	}
}