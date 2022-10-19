package com.duan.chapter5_kaoshixitong.entity;

import java.util.ArrayList;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;



public class ExamPaper {

	List<Question> allst = new ArrayList<Question>();



	public List<Question> getAllst() {

		return allst;

	}



	public void setAllst(List<Question> allst) {

		this.allst = allst;

	}

}
