package com.example.test1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.MemberService;
import com.google.gson.Gson;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@RequestMapping("/meber/login.do") // @는 spring에서는 아주 중요한 역할을 한다. 자바에서는 오버라이딩을 한다. 주소를 만들어준다.
	public String login(Model model) throws Exception {

		return "/member/member-login";
	}
	
	@RequestMapping(value = "/member/login.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") // db데이터를 가져오는 주소
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = memberService.login(map);
		
		return new Gson().toJson(resultMap); 
	}
}
