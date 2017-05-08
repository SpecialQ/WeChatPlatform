package com.AsiaApe.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AsiaApe.service.WeChatSecurityService;
import com.AsiaApe.service.base.ApplicationContextManager;

/**
 * 微信验证服务Controller
 * @author SpecialQ
 */
@Controller
public class VerificationController {
	
	private static final Logger logger = LoggerFactory.getLogger(VerificationController.class);
	
	/**
	 * 验证服务器地址的有效性
	 * 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带四个参数：
	 * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * timestamp	时间戳
	 * nonce		随机数
	 * echostr		随机字符串
	 * 开发者通过检验signature对请求进行校验（下面有校验方式）。
	 * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，
	 * 则接入生效，成为开发者成功，否则接入失败。
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="WeChatService", method=RequestMethod.GET)
	public String verify(@RequestParam(name="signature") String signature,
			@RequestParam(name="timestamp") String timestamp,
			@RequestParam(name="nonce") String nonce,
			@RequestParam(name="echostr") String echostr) throws IOException {
		// 接收微信服务器以Get请求发送的4个参数
        logger.info("接收到网络请求数据如下：signatre[{}];timestamp[{}];nonce[{}];echostr[{}]", signature, timestamp, nonce, echostr);
        
        WeChatSecurityService WeChatSecurityService = 
        		(WeChatSecurityService)ApplicationContextManager.getBean("WeChatSecurityService", signature, timestamp, nonce);
        
        if (WeChatSecurityService.verify()) {
            logger.info("验证成功！");
            return echostr;
        }else{
        	logger.info("验证失败！");
        	return "";
        }
	}
}
