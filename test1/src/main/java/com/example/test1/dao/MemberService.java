package com.example.test1.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.MemberMapper;
import com.example.test1.model.Member;

@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	public HashMap<String, Object> login(HashMap<String, Object> map) {
	HashMap<String, Object>	resultMap = new HashMap<String, Object>();
	Member member = memberMapper.memberLogin(map);
	String messge = member != null ? "로그인 성공!" : "로그인 실패";	
	
	resultMap.put("msg", messge);
	
	return resultMap;
	}
}
