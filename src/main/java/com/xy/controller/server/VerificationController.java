package com.xy.controller.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xy.util.WeixinConnector;

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
			@RequestParam(name="echostr") String echostr) throws IOException{
		// ����΢�ŷ�������Get�����͵�4������
        logger.debug("���յ����������������£�signatre["+signature+"];timestamp["+timestamp+"];nonce["+nonce+"];echostr["+echostr+"]");
        
        WeixinConnector wxConn = new WeixinConnector(signature, timestamp, nonce);
        
        if (wxConn.verify()) {
            logger.debug("��֤�ɹ���");
            return echostr;
        }else{
        	logger.debug("��֤ʧ�ܣ�");
        	return "";
        }
	}
}
