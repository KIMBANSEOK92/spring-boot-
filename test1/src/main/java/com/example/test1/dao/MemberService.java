package com.example.test1.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.MemberMapper;
import com.example.test1.model.Member;

@Service
public class MemberService {
	
	@Autowired
	MemberMapper memberMapeer;
	
	@Autowired
	HttpSession session;
	
	// 3 비밀번호 해시 객체 생성
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public HashMap<String, Object> login(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Member member = memberMapeer.memberLogin(map);
		String message = ""; // 로그인 성공 실패 여부 메세지
		String result = ""; // 로그인 성공 실패 여부 메세지
		
		/* 해시 적용 후 버전 */
		if(member != null) {
			//아이디가 존재, 비밀번호 비교하기 전
			// 사용자가 보낸 비밀번호 map에서 꺼낸 후 해시화한 값과
			// member 객체 안에 있는 password와 비교
			
			// 3-2. 비밀번호 해시 값 비교
			Boolean loginFlg = passwordEncoder.matches((String) map.get("pwd"), member.getPassword());
			System.out.println(loginFlg);
			if(loginFlg) {
				// 비밀번호를 정상 입력했을 경우
				if(member.getCnt() >= 5) {
					// 비밀번호른 정상입력했지만, 이전에 5회 이상 틀린 경우	
					message = "비밀번호를 5회 이상 잘못 입력하셨습니다.";
					result = "fail";
				}
			} else {
			// 로그인 성공
//				cnt값을 0으로 초기화
				memberMapeer.updateCnt1(map);
				
				message = "로그인 성공!";
				result = "success";
				
				session.setAttribute("sessionId", member.getUserId());
				session.setAttribute("sessionName", member.getName());
				session.setAttribute("sessionStatus", member.getStatus());
				if(member.getStatus().equals("A")) { // 일반회원과 관리자를 구분하기 위해 조건문 작성
					resultMap.put("url", "/mgr/member/list.do");

				} else {
					resultMap.put("url", "/main.do");
				}
			}
			
		} else {
			//아아디가 맞지만, 비밀번호가 다른 경우
			memberMapeer.updateCnt(map); 
			message = "패스워드를 확인해주세요.";
			result = "fail";
		}
	

//		message = "아이디가 존재하지 않습니다.";
		/* 해시 적용 후*/
		

		
		/*해시 적용 전 버전*/		
//		String message = member != null ? "로그인 성공!" : "로그인 실패!";
//		String result = member != null ? "success" : "fail";
//		if(member != null && member.getCnt() >= 5) {
//			message = "비밀번호를 5회 이상 잘못 입력하셨습니다.";
//			result = "fail";
//		}else if(member != null) {		
//			// cnt값을 0으로 초기화
//			memberMapeer.updateCnt1(map);
//			
//			message = "로그인 성공!";
//			result = "success";
//			
//			session.setAttribute("sessionId", member.getUserId());
//			session.setAttribute("sessionName", member.getName());
//			session.setAttribute("sessionStatus", member.getStatus());
//			if(member.getStatus().equals("A")) { // 일반회원과 관리자를 구분하기 위해 조건문 작성
//				resultMap.put("url", "/mgr/member/list.do");
//			} else {
//				resultMap.put("url", "/main.do");
//			}
//			
//		} else {
//			map.put("userId", map.get("id")); 
//			Member idCheck = memberMapeer.memberCheck(map);
			
//			if(idCheck != null) {
//				// 로그인 실패 시 cnt 1증가
//				memberMapeer.updateCnt(map);
//				if(idCheck.getCnt() >= 5) {
//					message = "비밀번호를 5회 이상 잘못 입력하셨습니다.";	
//				} else {	
//				message = "패스워드를 확인해주세요.";
//				}
//			
//			} else {
//				message = "아이디가 존재하지 않습니다.";
//			}
//		}
		
		/* */
		resultMap.put("msg", message);
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	public HashMap<String, Object> check(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Member member = memberMapeer.memberCheck(map);
		
		String result = member != null ? "true" : "false";
		resultMap.put("result", result);
		
		return resultMap;
	}

	public HashMap<String, Object> logout(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// 세션정보 삭제하는 방법은 
		// 1개씩 키값을 이용해 삭제하거나, 전체를 한번에 삭제
		
		String message = session.getAttribute("sessionName") + "님 로그아웃 되었습니다.";
		resultMap.put("msg", message);
		
//		session.removeAttribute("sessionId"); // 1개씩 삭제
		
		session.invalidate(); // 세션정보 전체 삭제
		
		return resultMap;
	}
	
	public HashMap<String, Object> memberInsert(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		// 3-1. 비밀번호 암호화(해시)
		String hashPwd = passwordEncoder.encode((String) map.get("pwd"));
		map.put("hashPwd", hashPwd); // 값을 넣어야한다.
		int cnt = memberMapeer.memberAdd(map);
		if(cnt < 1) {
			resultMap.put("result", "fail");
		} else {
			resultMap.put("result", "success");
		}
		
		return resultMap;
	}
	
	public HashMap<String, Object> getMemberList(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Member> mgr = memberMapeer.selectMgrList(map);
			resultMap.put("list", mgr);
			resultMap.put("result", "success");
		} catch(Exception e) {
			resultMap.put("result", "fail");
			System.out.println(e.getMessage()); // 개발자가 어떤 오류인지 확인하는 용도
		}

		return resultMap;
	}
	
	public HashMap<String, Object> rollbackCnt(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			memberMapeer.updateCnt1(map);
			resultMap.put("result", "success");
		} catch(Exception e) {
			resultMap.put("result", "fail");
			System.out.println(e.getMessage()); // 개발자가 어떤 오류인지 확인하는 용도
		}

		return resultMap;
	}
	
	public HashMap<String, Object> getfindMember(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 리턴 member
			Member member = memberMapeer.findMember(map);
			if(member != null) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "fail");
			}
			
		} catch(Exception e) {
			resultMap.put("result", "fail");
			System.out.println(e.getMessage()); // 개발자가 어떤 오류인지 확인하는 용도
		}

		return resultMap;
	}
	
	public HashMap<String, Object> getupdatePwd(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Member member = memberMapeer.memberLogin(map);
			System.out.println(map);
			System.out.println(member.getPassword());
			System.out.println(map.get("pwd"));
			
			boolean	pwdFlg = passwordEncoder.matches((String) map.get("pwd"), member.getPassword());
			if(pwdFlg) {
				resultMap.put("result", "fail");
				resultMap.put("msg", "'비밀번호가 이전과 동일합니다");
			} else {
				String hashPwd = passwordEncoder.encode((String) map.get("pwd"));
				map.put("hashPwd", hashPwd);
				memberMapeer.updatePwd(map);
				resultMap.put("result", "success");
				
			}
		
			
		} catch(Exception e) {
			resultMap.put("result", "fail");
			System.out.println(e.getMessage()); // 개발자가 어떤 오류인지 확인하는 용도
		}
		
		return resultMap;
	}
}




