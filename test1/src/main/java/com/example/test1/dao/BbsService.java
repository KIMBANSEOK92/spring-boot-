package com.example.test1.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.BbsMapper;
import com.example.test1.model.Bbs;

@Service
public class BbsService {
	
	@Autowired
	BbsMapper bbsMapper;
	
	public HashMap<String, Object> getBbsList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Bbs> list = bbsMapper.selectBbsList(map);
//		int hit = bbsMapper.updateHit(map);
		
		resultMap.put("list", list);
//		resultMap.put("hit", hit);
		resultMap.put("result", "success");
		return resultMap;

	}	

	public HashMap<String, Object> addBbs(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		int hit = bbsMapper.insertBbs(map);
		
		resultMap.put("bbsNum", map.get("bbsNum"));
		resultMap.put("result", "success");
		return resultMap;

	}
	

}
