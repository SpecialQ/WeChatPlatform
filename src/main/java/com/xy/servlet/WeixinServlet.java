package com.xy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.xy.bean.message.TextMessage;
import com.xy.util.MessageUtil;
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
        out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String returnMessage = null;
		Map<String, String> map = null;
		try {
			map = MessageUtil.xmlToMap(req);
		} catch (DocumentException e) {
			returnMessage = null;
			e.printStackTrace();
		}
		String toUserName = map.get("ToUserName");
		String fromUserName = map.get("FromUserName");
		String msgType = map.get("MsgType");
		String content = map.get("Content");

		
		if (MessageUtil.MSG_TYPE_TEXT.equals(msgType)) { // 对文本消息进行处理
			TextMessage text = new TextMessage();
			text.setFromUserName(toUserName); // 发送和回复是反向的
			text.setToUserName(fromUserName);
			text.setMsgType(MessageUtil.MSG_TYPE_TEXT);
			text.setCreateTime(new Date().getTime());
			text.setContent("你发送的消息是：" + content);
			returnMessage = MessageUtil.textMessageToXML(text);
			text.setContent("你发送的消息是：" + returnMessage);
			returnMessage = MessageUtil.textMessageToXML(text);
			System.out.println(returnMessage);
		}
		out.print(returnMessage); // 将回应发送给微信服务器
		out.close();
	}

}
