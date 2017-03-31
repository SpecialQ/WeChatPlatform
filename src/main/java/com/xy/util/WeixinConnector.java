package com.xy.util;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

public class WeixinConnector {

	// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	private String signature;
	// 时间戳
	private String timestamp;
	// 随机数
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
	 * 加密/校验流程如下： 
	 * 1. 将token、timestamp、nonce三个参数进行字典序排序 
	 * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
	 * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * 
	 * @return boolean 返回验证是否正确，true 验证成功；false 验证失败；
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
		String securityCode = DigestUtils.sha1Hex(sb.toString()); // 使用commons-codec生成sha1字符串

		if (StringTool.trim(securityCode).equals(StringTool.trim(signature))) {
			return true;
		} else {
			return false;
		}
	}

}
