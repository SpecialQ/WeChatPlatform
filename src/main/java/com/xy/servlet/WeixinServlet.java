package com.xy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xy.util.WeixinConnector;

public class WeixinServlet extends HttpServlet {

	private static final long serialVersionUID = 5703001291951854817L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收微信服务器以Get请求发送的4个参数
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        
        WeixinConnector wxConn = new WeixinConnector(signature, timestamp, nonce);
        
        PrintWriter out = resp.getWriter();
        if (wxConn.verify()) {
            out.print(echostr);        // 校验通过，原样返回echostr参数内容
            System.out.println("验证成功");
        }else{
        	System.out.println("验证失敗");
        }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

}
