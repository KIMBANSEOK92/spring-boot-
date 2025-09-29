package com.example.test1.model;

import lombok.Data;

@Data // get.set 코드를 각자 안만들어줘도 되는 인폴트
public class Member {
	private String userId;
	private String password;
	private String name;
	private String birth;
	private String nickName;
	private String status;
	
}
