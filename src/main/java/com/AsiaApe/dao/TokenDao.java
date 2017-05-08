package com.AsiaApe.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.AsiaApe.bean.system.Token;

public class TokenDao {
	private final String KEY = "global:token";
	private final String HKEY_ACCESS_TOKEN = "accessToken";
	private final String KEY_EXPIRES_IN = "expiresIn";
	
	public TokenDao() {
	}
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public void save(Token token) {
		HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
		hashOperations.put(KEY, HKEY_ACCESS_TOKEN, token.getAccessToken());
		hashOperations.put(KEY, KEY_EXPIRES_IN, String.valueOf(token.getExpiresIn()));
		
	}
	
}
