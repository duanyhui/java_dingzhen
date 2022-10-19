package com.duan.chapter5_kaoshixitong.entity;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;



@Component

public class ServiceImpl implements PaperService {

	@Autowired

	JdbcTemplate jdbcTemplate;



	@Override

	public ExamPaper genPaper() {

		ExamPaper paper = new ExamPaper();

		String sql = "select knowledges from configure"; // 查考核知识范围

		String knowledges = jdbcTemplate.queryForObject(sql, String.class);

		/* 以下对单选题进行选题处理 */

		sql = "select sxamount from configure"; // 查配置得到单选数量

		int amount = jdbcTemplate.queryForObject(sql, Integer.class);

		if (amount > 0) {

			sql = "select sxscore from configure"; // 查配置得到每小题分数

			int score = jdbcTemplate.queryForObject(sql, Integer.class);

			Question q = new Question();

			q.type = 1; // 单选题型

			q.score = score;

			q.bh = pickst("danxuan", amount, knowledges); // 调抽题处理

			paper.allst.add(q); // 将单选题的组卷选题信息加入试卷中

			

		}

		

		/* 以下对多选题进行选题处理 */

		sql = "select mxamount from configure"; // 查配置得到单选数量

		amount = jdbcTemplate.queryForObject(sql, Integer.class);

		if (amount > 0) {

			sql = "select mxscore from configure"; // 查配置得到每小题分数

			int score = jdbcTemplate.queryForObject(sql, Integer.class);

			Question q = new Question();

			q.type = 2; // 单选题型

			q.score = score;

			q.bh = pickst("mxuan", amount, knowledges); // 调抽题处理

			paper.allst.add(q); // 将单选题的组卷选题信息加入试卷中

		}

		

		/* 以下对填空题进行选题处理 */

		sql = "select tkamount from configure"; // 查配置得到单选数量

		amount = jdbcTemplate.queryForObject(sql, Integer.class);

		//System.out.println(amount);

		if (amount > 0) {

			sql = "select tkscore from configure"; // 查配置得到每小题分数

			int score = jdbcTemplate.queryForObject(sql, Integer.class);

			Question q = new Question();

			q.type = 3; // 单选题型

			q.score = score;

			q.bh = pickst("tiankong", amount, knowledges); // 调抽题处理

			paper.allst.add(q); // 将单选题的组卷选题信息加入试卷中

		}

		return paper;

	} // 注入JDBC模板

		

	private List<Integer> pickst(String table, int count, String knowledges) {

		List<Integer> have = new ArrayList<Integer>(); // 存放选好的题的编号

		String sql = "select count(*) from " + table + " where knowledge in (" + knowledges + ")";

		int realAmout = jdbcTemplate.queryForObject(sql, Integer.class);	

		// 查可供抽取试题总数

		if (realAmout < count) {

			count = realAmout; // 不够数量，按实际数量抽题

		}

		int pick = 0; // 统计选题数量

		sql = "select number from " + table + " where knowledge in (" + knowledges + ")";

		List<Integer> result = jdbcTemplate.queryForList(sql, Integer.class);

		while (pick < count) { // 循环处理选count道试题

			int num = (int) (Math.random() * realAmout); // 随机叫号

			Integer n = result.get(num); // 根据随机数得到相应记录的试题编号

			if (!(have.contains(n))) { // 判该题是否已选过

				have.add(n); // 未选过，则选中该题

				pick = pick + 1;

			}

		}

		return have;

	}



	public int[] givescore(String answer[], String useranswer[], int score) { // 对解答进行判分

		int sum[] = { 0, 0 };

		for (int k = 0; k < answer.length; k++) {

				if (answer[k].equals(useranswer[k]))

					sum[0] += score; // 累计得分

				sum[1] += score; // 累计总分

			}

		return sum; // 返回结果含大题得分和大题总分

	}

	

}