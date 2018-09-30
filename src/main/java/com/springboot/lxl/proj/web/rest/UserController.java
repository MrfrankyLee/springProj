package com.springboot.lxl.proj.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.lxl.proj.utils.IpUtils;
import com.springboot.lxl.proj.utils.WebUtils;
import com.springboot.lxl.proj.web.vm.LoginVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import main.java.com.springboot.lxl.proj.common.RestResult;
import main.java.com.springboot.lxl.proj.common.RestUtils;
import main.java.com.springboot.lxl.proj.entity.UserLoginLog;
import main.java.com.springboot.lxl.proj.repository.UserLoginLogRepository;
import main.java.com.springboot.lxl.proj.service.UserService;
import main.java.com.springboot.lxl.proj.utils.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import  com.springboot.lxl.proj.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Member;
import java.time.ZonedDateTime;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
@RequestMapping("/api")
@Api(value = "用户相关接口", description = "用户相关接口")
@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserLoginLogRepository userLoginLogRepository;

    @Resource
    private UserService userService;

    @PostMapping("/doLogin")
    @ApiOperation(value = "登录 返回{token:令牌}", response = ApiResponse.class)
    public RestResult<LoginVM> doLogin(
            @RequestParam(required = true) @ApiParam(required = true, value = "手机号") String mobile,
            @RequestParam(required = true) @ApiParam(required = true, value = "密码") String password,
            HttpServletRequest request) throws JsonProcessingException {

        String ipAddress = WebUtils.clientIP(request);
        String ipRegion =  IpUtils.getIpLocationResult(ipAddress);

        if (StringUtils.isBlank(mobile) || !ValidateUtils.isMobile(mobile)) {
            log.info("手机号码: {}", mobile);
            UserLoginLog loginLog = new UserLoginLog(mobile, ipAddress, "失败", "手机号或密码错误", ZonedDateTime.now(),ipRegion,"1");
            userLoginLogRepository.save(loginLog);
            return RestUtils.error("手机号或密码错误");
        }
        // 判断手机号是否注册
        User user =userService.getByMobile(mobile);

        if (user == null) {
            log.info("手机号码: {}", mobile);
            return RestUtils.error("手机号或密码错误");
        }
        String errorMsg = userService.getLoginErrorTime(mobile,user.getId());

        if(StringUtils.isNotBlank(errorMsg)){
            UserLoginLog loginLog = new UserLoginLog(mobile, ipAddress, "失败", errorMsg,ZonedDateTime.now(),ipRegion,"1");
            userLoginLogRepository.save(loginLog);
            return RestUtils.error(errorMsg);
        }
        LoginVM memberLogin = new LoginVM();
        user = userService.doLogin(mobile, password, ipAddress);
        if(user != null){
            BeanUtils.copyProperties(user, memberLogin);
        }
        //String token = tokenProvider.createToken(memberLogin, false);
        //memberLogin.setToken(token);
        userService.removeLoginErrorTime(mobile);
        UserLoginLog loginLog = new UserLoginLog(mobile, ipAddress, "成功", null,ZonedDateTime.now(),ipRegion,"0");
        userLoginLogRepository.save(loginLog);
        return RestUtils.ok();
    }
}
