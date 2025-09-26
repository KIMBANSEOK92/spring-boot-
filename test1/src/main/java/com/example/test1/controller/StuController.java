package com.example.test1.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.StuService;
import com.google.gson.Gson;

@Controller // @Controller를 꼭 만들어줘야 정상 페이지를 작동 할 수 있다.
public class StuController {
	
	@Autowired
	StuService stuService;

	@RequestMapping("/stu-list.do") // @는 spring에서는 아주 중요한 역할을 한다. 자바에서는 오버라이딩을 한다. 주소를 만들어준다.
	public String login(Model model) throws Exception {

		return "/stu-list";
	}
	@RequestMapping("/stu-view.do") // @는 spring에서는 아주 중요한 역할을 한다. 자바에서는 오버라이딩을 한다. 주소를 만들어준다.
	public String view(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		System.out.println(map);
		request.setAttribute("stuName", map.get("stuName"));
		return "/board-view";
	}
	
	@RequestMapping(value = "/stu-info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") // db데이터를 가져오는 주소
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map);
		resultMap = stuService.stuInfo(map);

		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/stu-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") // db데이터를 가져오는 주소
	@ResponseBody
	public String stuList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = stuService.getStuList(map);

		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu-delete.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") // db데이터를 가져오는 주소
	@ResponseBody
	public String deleteList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = stuService.deleteStuList(map);

		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu-view.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String studentView(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = stuService.getStudent(map);

		return new Gson().toJson(resultMap);
	}
}