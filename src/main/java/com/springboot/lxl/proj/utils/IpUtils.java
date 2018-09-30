package com.springboot.lxl.proj.utils;

import com.alibaba.fastjson.JSON;
import com.springboot.lxl.proj.common.IpLocationResult;
import com.springboot.lxl.proj.common.LocationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP地址工具类
 * @author lxl
 *
 */
public class IpUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(IpUtils.class);
    private static final String URL = "http://ip.taobao.com/service/getIpInfo.php";
    private IpUtils(){
        
    }
    
    public static String getIp() {
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            LOG.error("错误", e);
            return null;
        }
        return addr.getHostAddress().toString();// 获得本机IP
    }
    
    /**
     * 以ip地址获取地理位置信息等，返回一个对象模型
     * @param ipAddress
     * */
    public static String getIpLocationResult(String ipAddress){
        IpLocationResult ipLocationResult = new IpLocationResult();
        String param = "ip=" + ipAddress;
        String httpResponse = null;
        String result = null;
        try {
            httpResponse = HttpRequest.sendGet(URL, param);
        } catch (Exception e) {
            LOG.info("发送GET请求时发生错误", e.getMessage());
        }
        try {
            ipLocationResult = JSON.parseObject(httpResponse, IpLocationResult.class);
            if(ipLocationResult.getCode()==0){
                LocationResult locationResult = ipLocationResult.getData().get(0);
                String country = locationResult.getCountry();
                String province = locationResult.getRegion();
                String city = locationResult.getCity();
                if("中国".equals(country)){
                    result = province.equals(city) ? province : (province+"省"+city+"市");
                }else{
                    result = country+province;
                }
            }else{
                result = "未知";
            }
            return result;
        } catch (Exception e) {
            result = "本地或未知IP区域";
            return result;
        }
    }
    
    public static IpLocationResult getIpLocation(String ipAddress){
        String param = "ip=" + ipAddress;
        String httpResponse = null;
        IpLocationResult ipLocationResult = new IpLocationResult();
        try {
            httpResponse = HttpRequest.sendGet(URL, param);
        } catch (Exception e) {
            LOG.info("发送GET请求时发生错误", e.getMessage());
        }
        try {
            ipLocationResult = JSON.parseObject(httpResponse, IpLocationResult.class);
        } catch (Exception e) {
            LOG.info("本地或未知IP区域");
        }
        return ipLocationResult;
    }
    
    public static void main(String[] args) {
        String ipAddress = "106.12.112.217";
        System.out.println(JSON.toJSONString(getIpLocation(ipAddress)));
    }
}
