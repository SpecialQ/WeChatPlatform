package com.xy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.xy.bean.message.TextMessage;

/**
 * ʵ����Ϣ�ĸ�ʽת��(Map���ͺ�XML�Ļ�ת)
 * 
 * @author yangs
 */
public class MessageUtil {
	/**
	 * ��ͨ��Ϣ����
	 */
	public static final String MSG_TYPE_TEXT = "text";
	public static final String MSG_TYPE_IMAGE = "image";
	public static final String MSG_TYPE_VOICE = "voice";
	public static final String MSG_TYPE_VIDEO = "video";
	public static final String MSG_TYPE_SHORTVIDEO = "shortvideo";
	public static final String MSG_TYPE_LOCATION = "location";
	public static final String MSG_TYPE_LINK = "link";

	/**
	 * ��XMLת����Map����
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader(); // ʹ��dom4j����xml
		InputStream ins = request.getInputStream(); // ��request�л�ȡ������
		Document doc = reader.read(ins);

		Element root = doc.getRootElement(); // ��ȡ��Ԫ��
		List<Element> list = root.elements(); // ��ȡ���нڵ�

		for (Element e : list)
			map.put(e.getName(), e.getText());
		
		ins.close();
		return map;
	}

	/**
	 * ���ı���Ϣ����ת����XML
	 */
	public static String textMessageToXML(TextMessage textMessage) {

		XStream xstream = new XStream(); // ʹ��XStream��ʵ�����ʵ��ת����xml��ʽ
		xstream.alias("xml", textMessage.getClass()); // ��xml��Ĭ�ϸ��ڵ��滻�ɡ�xml��
		return xstream.toXML(textMessage);

	}
}
