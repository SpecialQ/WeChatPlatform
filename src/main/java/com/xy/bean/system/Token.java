package com.xy.bean.system;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Token 微信凭证
 * 
 * @author yangs
 */
public class Token {
	// 接口访问凭证]
	@JsonProperty("access_token")
    private String accessToken;
	
    // 凭证有效期，单位：秒
	@JsonProperty("expires_in")
    private int expiresIn;
    
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
    
    @Override
    public String toString() {
    	return "accessToken{"+accessToken+"},expiresIn{"+expiresIn+"}";
    }
}
