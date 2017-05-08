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
 * ΢����֤����Controller
 * @author SpecialQ
 */
@Controller
public class VerificationController {
	
	private static final Logger logger = LoggerFactory.getLogger(VerificationController.class);
	
	/**
	 * ��֤��������ַ����Ч��
	 * �������ύ��Ϣ��΢�ŷ�����������GET������д�ķ�������ַURL�ϣ�GET����Я���ĸ�������
	 * signature	΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
	 * timestamp	ʱ���
	 * nonce		�����
	 * echostr		����ַ���
	 * ������ͨ������signature���������У�飨������У�鷽ʽ����
	 * ��ȷ�ϴ˴�GET��������΢�ŷ���������ԭ������echostr�������ݣ�
	 * �������Ч����Ϊ�����߳ɹ����������ʧ�ܡ�
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="WeChatService", method=RequestMethod.GET)
	public String verify(@RequestParam(name="signature") String signature,
			@RequestParam(name="timestamp") String timestamp,
			@RequestParam(name="nonce") String nonce,
			@RequestParam(name="echostr") String echostr) throws IOException {
		// ����΢�ŷ�������Get�����͵�4������
        logger.info("���յ����������������£�signatre[{}];timestamp[{}];nonce[{}];echostr[{}]", signature, timestamp, nonce, echostr);
        
        WeChatSecurityService WeChatSecurityService = 
        		(WeChatSecurityService)ApplicationContextManager.getBean("WeChatSecurityService", signature, timestamp, nonce);
        
        if (WeChatSecurityService.verify()) {
            logger.info("��֤�ɹ���");
            return echostr;
        }else{
        	logger.info("��֤ʧ�ܣ�");
        	return "";
        }
	}
}
