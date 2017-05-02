package com.xy.quartz;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenQuartz {
	
	@Scheduled(cron = "0 0 0/2 * * *")
	public void execute(){
		long ms = System.currentTimeMillis();
		System.out.println("hello world!!!"+new Date(ms));
	}

}
