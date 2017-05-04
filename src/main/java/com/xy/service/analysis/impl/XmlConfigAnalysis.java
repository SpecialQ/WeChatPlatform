package com.xy.service.analysis.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.xy.service.analysis.ConfigAnalysis;
import com.xy.util.AppConstants;

@Component
public class XmlConfigAnalysis implements ConfigAnalysis {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlConfigAnalysis.class);
	
	public static final String CONFIG_KEY_APPID = "AppID";
	public static final String CONFIG_KEY_APPSECRET = "AppSecret";
	public static final String CONFIG_KEY_TOKEN = "Token";
	public static final String CONFIG_KEY_ENCODINGAESKEY = "EncodingAESKey";
	
	public static final String CONFIG_XPATH_APPID = "//configuration/AppID";
	public static final String CONFIG_XPATH_APPSECRET = "//configuration/AppSecret";
	public static final String CONFIG_XPATH_TOKEN = "//configuration/Token";
	public static final String CONFIG_XPATH_ENCODINGAESKEY = "//configuration/EncodingAESKey";
	
	Document document = null;
	
	// WeChatConfig�����ļ����·��
	private String weChatConfigFilePath;
	
	public void setWeChatConfigFilePath(String weChatConfigFilePath) {
		this.weChatConfigFilePath = weChatConfigFilePath;
	}
	
	public void init() {
		logger.info("����"+weChatConfigFilePath+"������Ϣ��");
		try {
			loadConfigFile(weChatConfigFilePath);
			logger.info("����"+weChatConfigFilePath+"�����ļ�{}��", AppConstants.SUCCESS);
		} catch (DocumentException e) {
			logger.error("����"+weChatConfigFilePath+"������Ϣ{}!", AppConstants.FAILD);
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<String, String> parseConfig() {
		Map<String, String> configInfo = new HashMap<String, String>();
		try {
			configInfo.put(CONFIG_KEY_APPID, getXmlConfigInfo(CONFIG_XPATH_APPID));
			configInfo.put(CONFIG_KEY_APPSECRET, getXmlConfigInfo(CONFIG_XPATH_APPSECRET));
			configInfo.put(CONFIG_KEY_TOKEN, getXmlConfigInfo(CONFIG_XPATH_TOKEN));
			configInfo.put(CONFIG_KEY_ENCODINGAESKEY, getXmlConfigInfo(CONFIG_XPATH_ENCODINGAESKEY));
		} catch (DocumentException e) {
			logger.error("����"+weChatConfigFilePath+"������Ϣ{}!", AppConstants.FAILD);
			e.printStackTrace();
		}
		logger.info("����"+weChatConfigFilePath+"������ϢΪ��{}",configInfo.toString());
		return configInfo;
	}
	
	private void loadConfigFile(String filePath) throws DocumentException{
		SAXReader saxReader = new SAXReader();
		// ��resourcesĿ¼�¼�����Դ�ļ�
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
		document = saxReader.read(inputStream);
	}

	private String getXmlConfigInfo(String xPath) throws DocumentException {
		Node node = document.selectSingleNode(xPath);
		if(!(node.getText().equals(AppConstants.EMPTY_STRING)))
			return StringUtils.trimWhitespace(node.getText());
		return StringUtils.trimWhitespace(node.valueOf("@value"));
	}

}
