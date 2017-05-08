package com.AsiaApe.service.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.AsiaApe.service.analysis.ConfigAnalysis;

@Component
public class ConfigurationManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);
	
	private static Map<String, String> configInfo;
	
	private List<ConfigAnalysis> analysisList;
	
	// ��ʼ��״̬��־��true���ѳ�ʼ����false��δ��ʼ��
	private boolean initFlag = false;
	
	public static String getConfigValue(String key){
		return configInfo.get(key);
	}
	
	public void init(){
		logger.info("��ʼ��������Ϣ��������");
		if(configInfo == null)
			configInfo = new HashMap<String, String>();
		if(initFlag)
			return;
		loadAnalysis();
		initFlag = true;
	}
	
	private void loadAnalysis(){
		for (ConfigAnalysis configAnalysis : analysisList) {
			Map<String, String> configMap = configAnalysis.parseConfig();
			configInfo.putAll(configMap);
			logger.info("����������Ϣ��{}", configMap.toString());
		}
	}
	
	public void setAnalysisList(List<ConfigAnalysis> analysisList) {
		this.analysisList = analysisList;
	}
	
}
