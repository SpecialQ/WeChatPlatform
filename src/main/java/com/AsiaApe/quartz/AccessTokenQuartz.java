package com.AsiaApe.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.AsiaApe.bean.system.Token;
import com.AsiaApe.service.AccessTokenService;

@Component
public class AccessTokenQuartz {
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	@Scheduled(fixedRate = 2 * 60 * 60 * 1000)
	public void execute() {
		Token token = accessTokenService.getToken();
		System.out.println(token.toString());
	}

}
