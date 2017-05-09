package com.AsiaApe.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.AsiaApe.bean.system.Token;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessTokenService.class);
	
	// ΢���ṩ��ƾ֤��ȡ��URL��ַ��GET��
    public String tokenUrl;
    
    @Value("${WeChat.AppID}")
    private String appId;
    @Value("${WeChat.AppSecret}")
    private String appSecret;
    
    public void setTokenUrl(String tokenUrl){
    	this.tokenUrl = tokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
    }
    
    public Token getToken(){
    	Token token = null;
    	OkHttpClient client = new OkHttpClient();
    	Request request = new Request.Builder().url(tokenUrl).build();
    	try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				ObjectMapper objectMapper = new ObjectMapper();
				token = objectMapper.readValue(response.body().string(), Token.class);
		    } else {
		    	throw new IOException("Unexpected code " + response);
		    }
		} catch (IOException e) {
			logger.error("΢��ƾ֤��ȡ�쳣������URL��ַΪ��{}", tokenUrl);
			e.printStackTrace();
		}
    	return token;
    }
    
}
