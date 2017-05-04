package com.xy.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xy.bean.system.Token;
import com.xy.service.AccessTokenService;
import com.xy.service.base.ApplicationContextManager;

@Component
public class AccessTokenQuartz {
	
	@Scheduled(fixedRate = 2 * 60 * 60 * 1000)
	public void execute() {
		AccessTokenService accessTokenService = (AccessTokenService)ApplicationContextManager.getBean("AccessTokenService");
		Token token = accessTokenService.getToken();
		System.out.println(token.toString());
	}

}
