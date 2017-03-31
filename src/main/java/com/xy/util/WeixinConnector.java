package com.xy.util;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

public class WeixinConnector {

	// ΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
	private String signature;
	// ʱ���
	private String timestamp;
	// �����
	private String nonce;

	private final String token = "xyyxjlb";

	/**
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 */
	public WeixinConnector(String signature, String timestamp, String nonce) {
		this.signature = signature;
		this.timestamp = timestamp;
		this.nonce = nonce;
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
		if (StringTool.isEmpty(signature) || 
				StringTool.isEmpty(timestamp) || 
				StringTool.isEmpty(nonce)) {
			return false;
		}
		String[] ArrTmp = { token, timestamp, nonce };
		Arrays.sort(ArrTmp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ArrTmp.length; i++) {
			sb.append(ArrTmp[i]);
		}
		String securityCode = DigestUtils.sha1Hex(sb.toString()); // ʹ��commons-codec����sha1�ַ���

		if (StringTool.trim(securityCode).equals(StringTool.trim(signature))) {
			return true;
		} else {
			return false;
		}
	}

}
