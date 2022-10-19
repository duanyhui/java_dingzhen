package com.duan.chapter5_kaoshixitong.entity;


import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;

import java.net.URLEncoder;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

@Controller

public class ExamController {

	@Autowired

	PaperService paperService;

	@Autowired

	JdbcTemplate jdbcTemplate;



	@RequestMapping(value = "/", method = RequestMethod.GET)

	public String enter(HttpServletRequest request) {

		request.getSession().setAttribute("username", "user2");  //此处假定获取登录账户

		return "redirect:/disppaper";

	}



	@RequestMapping(value = "/disppaper", method = RequestMethod.GET)

	public String display(Model model) {

		ExamPaper paper = paperService.genPaper(); // 调组卷算法组卷

		int len = paper.allst.size(); // 求试题大类数量

		DisplayPaper[] disp = new DisplayPaper[len]; // 存放显示的试卷内容

		for (int k = 0; k < len; k++) {

			Question q = paper.allst.get(k); // 第k个题型

			disp[k] = new DisplayPaper();

			disp[k].type = q.type;

			disp[k].name = getTxName(q.type); // 获取题型名称

			disp[k].score = q.score;

			for (int i = 0; i < q.bh.size(); i++) // 获取此类试题的各题内容

				disp[k].content.add(getContent(q.bh.get(i), q.type));

		}

		Gson gson = new Gson();

		String x1 = "";

		try {

			x1 = URLEncoder.encode(gson.toJson(paper), "utf-8");

		} catch (UnsupportedEncodingException e) {

		}

		model.addAttribute("paper", x1); // 传递试卷的Json串用于后续判分处理等

		model.addAttribute("disppaper", disp);

		return "display";

	}



	private String getTxName(int type) { // 根据题型编码得到题型名称

		switch (type) {

		case 1:

			return "单选题";

		case 2:

			return "多选题";

		case 3:

			return "填空题";

		}

		return null;

	}



	private String getContent(int bh, int type) { // 根据题型编码和题型查试题内容

		String sql = null;

		switch (type) {

		case 1: // 单选

			sql = "select  content from danxuan where number=" + bh;

			break;

		case 2: // 多选

			sql = "select  content from mxuan where number=" + bh;

			break;

		case 3: // 填空

			sql = "select  content from tiankong where number=" + bh;

			break;

		}

		return (String) jdbcTemplate.queryForObject(sql, String.class);

	}



	private String getAnswer(int bh, int type) { // 根据题型编码和题型查试题内容

		String sql = null;

		switch (type) {

		case 1: // 单选

			sql = "select answer from danxuan where number=" + bh;

			break;

		case 2: // 多选

			sql = "select answer from mxuan where number=" + bh;

			break;

		case 3: // 填空

			sql = "select answer from tiankong where number=" + bh;

			break;

		}

		return (String) jdbcTemplate.queryForObject(sql, String.class);

	}



	@RequestMapping(value = "/givemark/{paper}", method = RequestMethod.POST)

	public String marking(@PathVariable("paper") String paper, Model model, HttpServletRequest request) {

		int total = 0; // 用来统计总分值

		int getscore = 0; // 用来累计用户得分

		ArrayList<String[]> allanswer = new ArrayList<String[]>(); // 用户解答

		try {

			paper = URLDecoder.decode(paper, "utf-8"); // 进行URL解码

		} catch (UnsupportedEncodingException e) {

		}

		Gson gson = new Gson();

		// 以下解开JSON串恢复试卷

		ExamPaper p = (ExamPaper) gson.fromJson(paper, ExamPaper.class);

		for (int k = 0; k < p.allst.size(); k++) { // 循环处理各类题型

			Question me = p.allst.get(k); // 获取第k个大题

			String ans[] = new String[me.bh.size()]; // 定义记录标准答案的数组

			String usera[] = new String[me.bh.size()]; // 定义记录学生解答的数组

			for (int i = 0; i < ans.length; i++) {

				ans[i] = getAnswer(me.bh.get(i), me.type); // 获取标准答案

				if (me.type == 2) {

					String myans[] = request.getParameterValues("data" + (k + 1) + "-" + (i + 1)); // 读解答

					usera[i] = "";

					for (String m : myans)

						usera[i] += m;

				} else

					usera[i] = request.getParameter("data" + (k + 1) + "-" + (i + 1)); // 读解答

			}

			int score[] = paperService.givescore(ans, usera, me.score); // 评分

			allanswer.add(usera); // 将本大题的用户解答加入列表中

			total += score[1]; // 计算整卷总分值

			getscore += score[0]; // 计算整卷总得分

		}

		int lastscore = (int) (getscore * 100.0 / total); // 将用户得分转百分制

		model.addAttribute("score", lastscore);

		String user = (String) request.getSession().getAttribute("username");

		// String user = request.getRemoteUser(); // 取得用户标识

		// 登记用户解答，试卷为paper参数，解答为allanswer列表

		String sql = "select count(*) from paperlog where username='" + user + "'";

		int x = jdbcTemplate.queryForObject(sql, Integer.class);

		if (x > 0) // 只记录最新测试试卷，如以前有测试记录则更新、否则插入

			sql = "update paperlog set paper='" + paper + "',useranswer='" + gson.toJson(allanswer)

					+ "' where username='" + user + "'";

		else

			sql = "insert into paperlog(username,paper,useranswer) values('" + user + "','" + paper + "','"

					+ gson.toJson(allanswer) + "')";

		jdbcTemplate.update(sql);

		return "score"; // 用score.jsp视图文件显示得分

	}



	@RequestMapping(value = "/searchpaper", method = RequestMethod.GET)

	public String searchpaper(Model model, HttpServletRequest request) {

		// String user = request.getRemoteUser(); // 获取用户标识

		String user = (String) request.getSession().getAttribute("username");

		String sql = "select * from paperlog where username='" + user + "'";

		List<Map<String, Object>> x = jdbcTemplate.queryForList(sql);

		List<PaperCompare> me = new ArrayList<PaperCompare>(); // 所有大题的数据

		Gson json = new Gson();

		List<String[]> x2; // 学生解答

		if (x.size() > 0) {

			ExamPaper x1 = (ExamPaper) (json.fromJson((String) (x.get(0).get("paper")), ExamPaper.class));

			x2 = (ArrayList<String[]>) (json.fromJson((String) (x.get(0).get("useranswer")),

					new TypeToken<ArrayList<String[]>>() {

					}.getType()));

			for (int k = 0; k < x1.allst.size(); k++) { // 处理各大类题型

				Question q = x1.allst.get(k); // 第k大类题型

				PaperCompare pac = new PaperCompare();

				pac.name = getTxName(q.type);

				for (int i = 0; i < q.bh.size(); i++) { // 对这类试题的每道题

					Map<String, Object> onest = new HashMap<String, Object>();

					onest.put("content", getContent(q.bh.get(i), q.type)); // 试题内容

					onest.put("answer", getAnswer(q.bh.get(i), q.type)); // 标准答案

					onest.put("solution", x2.get(k)[i]); // 用户解答

					pac.content.add(onest); // 将Map对象加入列表中

				}

				me.add(pac);

			}

		}

		model.addAttribute("paper", me);

		return "searchpaper";

	}



}