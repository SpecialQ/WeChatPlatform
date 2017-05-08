package com.AsiaApe.service.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextManager implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextManager.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getContext(){
		return applicationContext;
	}
	
	public static Object getBean(String beanName) {  
        return applicationContext.getBean(beanName);  
    }  
      
    public static <T>T getBean(String beanName, Class<T>clazz) {  
        return applicationContext.getBean(beanName , clazz);  
    }
    
    public static Object getBean(String beanName, Object... args ){
    	return applicationContext.getBean(beanName, args);
    }
}