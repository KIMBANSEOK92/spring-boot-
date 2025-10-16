package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Menu;
import com.example.test1.model.Product;

@Mapper
public interface ProductMapper {
	
	
	List<Product> selectProductList(HashMap<String, Object> map);
	
	// 제품 목록
	List<Menu> selectMenuList(HashMap<String, Object> map);
	
	// 제품 등록
	int insertMenu(HashMap<String, Object> map);
	
	// 제품 이미지 등록
	int insertMenuImg(HashMap<String, Object> map);
	
	// 제품 상제정보
	Product selectProduct(HashMap<String, Object> map);
	
	// 제품 구매정보 등록
	int insertPayment(HashMap<String, Object> map);
}
