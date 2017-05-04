package com.xy.service;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xy.service.analysis.impl.XmlConfigAnalysis;
import com.xy.service.base.ConfigurationManager;

/**
 * 
 * ΢��ƽ̨�İ�ȫ��Ϣ����
 * @author SpecialQ
 */
@Service
public class WeChatSecurityService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeChatSecurityService.class);
	
	// ΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
	private String signature;
	// ʱ���
	private String timestamp;
	// �����
	private String nonce;
	// ����
	private String token;
	
	public WeChatSecurityService() {
	}
	
	public WeChatSecurityService(String signature, String timestamp, String nonce) {
		this.signature = signature;
		this.timestamp = timestamp;
		this.nonce = nonce;
		this.token = ConfigurationManager.getConfigValue(XmlConfigAnalysis.CONFIG_KEY_TOKEN);
	}
	
	/**
	 * ����/У���������£� 
	 * 1. ��token��timestamp��nonce�������������ֵ������� 
	 * 2. �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
	 * 3. �����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
	 * 
	 * @return boolean ������֤�Ƿ���ȷ��true ��֤�ɹ���false ��֤ʧ�ܣ�
	 */
	public boolean verify() {
		logger.debug("����У���������£�signatre[{}];timestamp[{}];nonce[{}];token[{}]", signature, timestamp, nonce, token);
		if (StringUtils.isEmpty(signature) || 
				StringUtils.isEmpty(timestamp) || 
				StringUtils.isEmpty(nonce)) {
			return false;
		}
		String[] ArrTmp = { token, timestamp, nonce };
		Arrays.sort(ArrTmp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ArrTmp.length; i++) {
			sb.append(ArrTmp[i]);
		}
		String securityCode = DigestUtils.sha1Hex(sb.toString()); // ʹ��commons-codec����sha1�ַ���

		if (StringUtils.trimWhitespace(securityCode).equals(StringUtils.trimWhitespace(signature))) {
			return true;
		} else {
			logger.error("����Signature��Ϊ��[{}]�����ܵ�Signature��Ϊ��[{}]",signature,securityCode);
			return false;
		}
	}
	
}
