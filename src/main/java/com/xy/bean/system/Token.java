package com.xy.bean.system;

/**
 * Token ΢��ƾ֤
 * 
 * @author yangs
 */
public class Token {
	// �ӿڷ���ƾ֤
    private String accessToken;
    // ƾ֤��Ч�ڣ���λ����
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
    	return "accessToken:{"+accessToken+"},expiresIn{"+expiresIn+"}";
    }
}
