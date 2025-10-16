package com.example.test1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.BbsService;
import com.google.gson.Gson;

@Controller
public class BbsController {
	
	@Autowired
	BbsService bbsService;
	
	@RequestMapping("/bbs/list.do") 
    public String list(Model model) throws Exception{ 
		
        return "/bbs/list";
    }
	
	@RequestMapping("/bbs/add.do") 
    public String add(Model model) throws Exception{ 
		
        return "/bbs/add";
    }
	
	@RequestMapping(value = "/bbs/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String boardList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = bbsService.getBbsList(map);
		
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/bbs/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = bbsService.addBbs(map);
		
		return new Gson().toJson(resultMap);
	}
}
