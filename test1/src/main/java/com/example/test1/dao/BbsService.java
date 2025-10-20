
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
	
	public HashMap<String, Object> getBbsList(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Bbs> list = bbsMapper.selectBbsList(map);
			int cnt = bbsMapper.selectBbsListCnt(map);
			
			resultMap.put("cnt", cnt);
			resultMap.put("list", list);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		
		
		return resultMap;		
	}
	
	public HashMap<String, Object> addBbs(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			bbsMapper.insertBbs(map);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		
		
		return resultMap;		
	}
	
	public HashMap<String, Object> removeBbs(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			System.out.println(map);
			bbsMapper.deleteBbs(map);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		
		
		return resultMap;		
	}
	
	public HashMap<String, Object> getBbs(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Bbs info = bbsMapper.selectBbs(map);
			resultMap.put("info", info);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		
		
		return resultMap;		
	}
	
	public HashMap<String, Object> editBbs(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			bbsMapper.updateBbs(map);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		
		
		return resultMap;		
	}
}





