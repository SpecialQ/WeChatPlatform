package com.AsiaApe.bean.system;

public class ConfigInfo {
	// Ӧ��ID
	private String AppID;
	// Ӧ����Կ
	private String AppSecret;
	// ����
	private String Token;
	// ��Ϣ�ӽ�����Կ
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
