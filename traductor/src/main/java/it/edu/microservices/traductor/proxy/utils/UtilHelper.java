package it.edu.microservices.traductor.proxy.utils;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UtilHelper {

	private final static Logger LOGGER = LogManager.getLogger(UtilHelper.class);

	public static String getHostName() {
		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "UnknownHostName";
	}

	public static String getActiveProfile(String[] args) {
		String profile = "local";
		if (args.length > 0 && args[0] != null) {
			LOGGER.info("The system will be configured with profile : " + args[0]);
			return args[0];
		}
		LOGGER.info("The system will be configured with profile : " + profile);
		return profile;
	}
	
	public static Map<String,String>  buildVersionInfo(Properties gitProperties) {
		Map<String, String> version = new HashMap<>();
		if (gitProperties != null) {
			for (GitProperties propertie : GitProperties.values()) {
				if (propertie.isToDisplay()) {
					version.put(propertie.name(), gitProperties.getProperty(propertie.getKey()));
				}
			}
		}
		return version;
	}

	public static String getCurrentDate() {
		return (new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
	}
}
