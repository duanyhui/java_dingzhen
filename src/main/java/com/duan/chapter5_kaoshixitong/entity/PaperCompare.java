package com.duan.chapter5_kaoshixitong.entity;

import java.util.ArrayList;

import java.util.List;

import java.util.Map;



public class PaperCompare {  

	String name;   // 该大题的题型名称

	List<Map<String,Object>> content=new ArrayList<Map<String,Object>>();

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public List<Map<String, Object>> getContent() {

		return content;

	}

	public void setContent(List<Map<String, Object>> content) {

		this.content = content;

	} 

}