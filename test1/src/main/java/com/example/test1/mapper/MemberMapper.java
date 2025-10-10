package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Member;

@Mapper
public interface MemberMapper {
	// 로그인
	Member memberLogin(HashMap<String, Object> map);
	
	// 아이디 체크
	Member memberCheck(HashMap<String, Object> map);
	
	// 가입
	int memberAdd(HashMap<String, Object> map);
	
	// 회원목록 관리자 기능
	List<Member> selectMgrList(HashMap<String, Object> map);
	
}

