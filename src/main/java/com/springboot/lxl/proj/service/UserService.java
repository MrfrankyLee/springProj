package main.java.com.springboot.lxl.proj.service;

import com.springboot.lxl.proj.entity.User;
import main.java.com.springboot.lxl.proj.exception.FieldValidationException;
import main.java.com.springboot.lxl.proj.security.Digests;
import main.java.com.springboot.lxl.proj.utils.JacksonJsonUtil;
import main.java.com.springboot.lxl.proj.web.vm.LoginErrorVM;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.lxl.proj.repository.UserRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private static final String LOGIN_ERROR_TIME = "crm:LoginError:";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserRepository userRepository;
    @Cacheable(key = "'crm:memberLogin:'+#mobile", value = "#result",unless="#result == null")
    public User getByMobile(String mobile) {
        return   userRepository.getByMobile(mobile);
    }

    @Transactional(readOnly = false)
    public String getLoginErrorTime(String mobile,Long memberId){
        String errorMsg = null;
        Serializable obj = null;//redisTemplate.opsForValue().get("crm:LoginError:" + mobile);
        if (obj != null) {
            LoginErrorVM error = JacksonJsonUtil.jsonToBean(obj.toString(), LoginErrorVM.class);
            if (error != null){
                Integer errorTime = error.getErrorTime();
                Date loginTime = error.getLoginTime();
                Date expire = error.getDeadline();
                Integer sendTime = error.getSendTime();
                if(redisTemplate.hasKey("crm:LoginError:" + mobile)){
                    removeLoginErrorTime(mobile);
                }
                if(errorTime < 3){
                    getLoginErrorVM(loginTime,mobile, errorTime+1, expire,sendTime);
                }else{
                    errorMsg = "密码错误，请1小时后在尝试！";
                    getLoginErrorVM(loginTime,mobile, errorTime, expire,1);
                }
            }
        }else{
            Date expire = DateUtils.addHours(new Date(), 1);
            Date loginTime = new Date();
            getLoginErrorVM(loginTime,mobile, 1, expire,0);
        }
        return errorMsg;
    }


    public void getLoginErrorVM(Date loginTime,String mobile,Integer errorTime,Date expire,Integer sendTime){
        LoginErrorVM loginError = new LoginErrorVM();
        loginError.setLoginTime(loginTime);
        loginError.setMobile(mobile);
        loginError.setErrorTime(errorTime);
        loginError.setDeadline(expire);
        loginError.setSendTime(sendTime);
        redisTemplate.opsForValue().set(LOGIN_ERROR_TIME + mobile, JacksonJsonUtil.beanToJson(loginError));
        redisTemplate.expireAt(LOGIN_ERROR_TIME + mobile, expire);
    }
    public void removeLoginErrorTime(String mobile){
        redisTemplate.delete(LOGIN_ERROR_TIME + mobile);
    }

    @Caching(put = { @CachePut(key = "'crm:memberLogin:'+#result.getMobile()", value = { "#result" }),
            @CachePut(key = "'crm:member:'+#result.getId()", value = { "#result" }) })
    public User doLogin(String loginName, String pwd, String ip) {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(pwd)) {
            throw new FieldValidationException("参数错误");
        }
        User member = getByMobile(loginName);
        if (member == null) {
            throw new FieldValidationException("mobile", "账号不存在");
        }

        String pwdString = entryptStr(pwd, member.getSalt());

        if (!pwdString.equals(member.getPassword())) {
            throw new FieldValidationException("password", "手机号或密码错误");
        }

        // 发布登录消息
        /*LoginMessageBean memberLogin = new LoginMessageBean();
        memberLogin.setMemberId(member.getId());
        memberLogin.setTerminal(terminal);
        memberLogin.setIp(ip);
        memberLoginEvent.fire(memberLogin);*/

        return member;
    }

    public String entryptStr(String password, String salt) {
        byte[] hashSalt = Digests.sha1(password.getBytes(), Hex.decode(salt), HASH_INTERATIONS);
        return new String(Hex.encode(hashSalt));
    }
}
