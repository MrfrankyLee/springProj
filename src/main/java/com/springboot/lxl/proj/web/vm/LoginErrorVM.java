package main.java.com.springboot.lxl.proj.web.vm;

import java.util.Date;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
public class LoginErrorVM {
    private String mobile;       // 手机号码
    private Integer errorTime;   // 错误次数
    private Date loginTime;      // 登录时间
    private Date deadline;       // 过期时间
    private Integer sendTime;    // 短信发送次数

    public LoginErrorVM() {
        super();
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Integer getErrorTime() {
        return errorTime;
    }
    public void setErrorTime(Integer errorTime) {
        this.errorTime = errorTime;
    }
    public Date getLoginTime() {
        return loginTime;
    }
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public Integer getSendTime() {
        return sendTime;
    }
    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }
}
