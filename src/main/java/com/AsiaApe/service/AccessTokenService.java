package com.AsiaApe.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.AsiaApe.bean.system.Token;
import com.AsiaApe.service.analysis.impl.XmlConfigAnalysis;
import com.AsiaApe.service.base.ConfigurationManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessTokenService.class);
	
	// 微信提供的凭证获取的URL地址（GET）
    public String tokenUrl;
    
    public void setTokenUrl(String tokenUrl) {
    	String appId = ConfigurationManager.getConfigValue(XmlConfigAnalysis.CONFIG_KEY_APPID);
    	String appSecret = ConfigurationManager.getConfigValue(XmlConfigAnalysis.CONFIG_KEY_APPSECRET);
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
			logger.error("微信凭证获取异常！请求URL地址为：{}", tokenUrl);
			e.printStackTrace();
		}
    	return token;
    }
    
}
