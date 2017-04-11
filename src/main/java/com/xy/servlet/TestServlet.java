package com.xy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xy.bean.system.Token;
import com.xy.dao.TokenDao;
import com.xy.util.CommonUtil;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 5176694223444564884L;

	private ApplicationContext applicationContext;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//TODO 封装applicationContext的获取方式
		applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		TokenDao tokenDao = (TokenDao)applicationContext.getBean("tokenDao");
		
		// 获取token信息并保存
		Token token = CommonUtil.getToken("wxdd3ce89bb92face3", "608acc09bac975a28bf520591ef58a62");
		System.out.println(token.toString());
		tokenDao.save(token);
		
		PrintWriter out = resp.getWriter();
		out.println("Hello world!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
