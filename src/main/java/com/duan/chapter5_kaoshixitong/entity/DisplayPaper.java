package com.duan.chapter5_kaoshixitong.entity;

import java.util.ArrayList;

import java.util.List;



public class DisplayPaper {

	List<String> content = new ArrayList<String>(); // 各小题的试题内容

	String name="无名"; // 该类试题名称

	int type; // 试题类型

	public List<String> getContent() {

		return content;

	}

	public void setContent(List<String> content) {

		this.content = content;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public int getType() {

		return type;

	}

	public void setType(int type) {

		this.type = type;

	}

	public int getScore() {

		return score;

	}

	public void setScore(int score) {

		this.score = score;

	}

	int score; // 小题分值

}































