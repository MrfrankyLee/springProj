package com.springboot.lxl.proj.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
	
	private static final Logger log = LoggerFactory.getLogger(WebUtils.class);
	
    public static String clientIP(HttpServletRequest request) {
    	String ip = "";
    	if(isCorrectIp(request.getHeader("headerX-Forwarded-Host"))){
    		ip= request.getHeader("headerX-Forwarded-Host");
    		log.info("headerX-Forwarded-Host:"+ip);
    	}else if(isCorrectIp(request.getHeader("X-Real-IP"))){
    		ip= request.getHeader("X-Real-IP");
    		log.info("X-Real-IP:"+ip);
    	}else if (isCorrectIp(request.getHeader("X-Forwarded-For"))) {
            ip = request.getHeader("X-Forwarded-For");
            log.info("X-Forwarded-For:"+ip);
        }else if (isCorrectIp(request.getHeader("Proxy-Client-IP"))) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info("Proxy-Client-IP:"+ip);
        }else if (isCorrectIp(request.getHeader("WL-Proxy-Client-IP"))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.info("WL-Proxy-Client-IP:"+ip);
        }else {
                ip = request.getRemoteAddr();
        }
        log.info("IP:"+ip);
        if (ip.contains(",")) {
            String[] ips = ip.split(",");
            ip = ips[0];
        }
        return ip;
    }

    private static boolean isCorrectIp(String ip) {
        return !((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip));
    }
}
