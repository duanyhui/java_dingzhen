package com.duan.chapter5_kaoshixitong.entity;

import java.util.List;



public class Question {

	 List<Integer> bh;  //各小题编号

	 int score ; //每小题分数

	 int type; //题型，值为1表示单选，为2表示多选

	public List<Integer> getBh() {

		return bh;

	}

	public void setBh(List<Integer> bh) {

		this.bh = bh;

	}

	public int getScore() {

		return score;

	}

	public void setScore(int score) {

		this.score = score;

	}

	public int getType() {

		return type;

	}

	public void setType(int type) {

		this.type = type;

	}

}
