package com.example.test1.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.ProductMapper;
import com.example.test1.model.Menu;
import com.example.test1.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductMapper productMapper;
	
	public HashMap<String, Object> getProductList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Product> list =  productMapper.selectProductList(map);
			List<Menu> menuList =  productMapper.selectMenuList(map);
			resultMap.put("list", list);
			resultMap.put("menuList", menuList);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		
		return resultMap;
	}

	public HashMap<String, Object> getMenuList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Menu> menuList =  productMapper.selectMenuList(map);
			resultMap.put("menuList", menuList);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		
		return resultMap;
	}
	
	public HashMap<String, Object> addFood(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int cnt = productMapper.insertMenu(map);
			resultMap.put("foodNo", map.get("foodNo"));
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		
		return resultMap;
	}
	
	public HashMap<String, Object> addFoodImg(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int cnt = productMapper.insertMenuImg(map);
			resultMap.put("foodNo", map.get("foodNo"));
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		
		}	
		
		return resultMap;
		
	}
	
	public HashMap<String, Object> getProduct(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Product product = productMapper.selectProduct(map);
			resultMap.put("info", product);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		
		}	
		
		return resultMap;

	}
	
	public HashMap<String, Object> addPayment(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			productMapper.insertPayment(map);
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		
		}	
		
		return resultMap;

	}
	
	
}
