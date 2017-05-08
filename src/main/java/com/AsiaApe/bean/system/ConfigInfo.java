package com.AsiaApe.bean.system;

public class ConfigInfo {
	// 应用ID
	private String AppID;
	// 应用密钥
	private String AppSecret;
	// 令牌
	private String Token;
	// 消息加解密密钥
	private String EncodingAESKey;
	
	public String getAppID() {
		return AppID;
	}
	public void setAppID(String appID) {
		AppID = appID;
	}
	public String getAppSecret() {
		return AppSecret;
	}
	public void setAppSecret(String appSecret) {
		AppSecret = appSecret;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getEncodingAESKey() {
		return EncodingAESKey;
	}
	public void setEncodingAESKey(String encodingAESKey) {
		EncodingAESKey = encodingAESKey;
	}
	
	@Override
	public String toString() {
		return "AppID{"+AppID+"},AppSecret{"+AppSecret+"},Token{"+Token+"},EncodingAESKey{"+EncodingAESKey+"}";
	}
}
