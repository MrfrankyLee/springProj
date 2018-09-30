package main.java.com.springboot.lxl.proj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
@Entity
@Table(name = "user_login_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "登录行为记录对象")
public class UserLoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value="ID",required=false)
    private Long id;

    @ApiModelProperty(value="手机号",required=true)
    private String mobile;

    @ApiModelProperty(value="ip",required=false)
    private String ip;

    @ApiModelProperty(value="ip区域:省市",required=false)
    private String ipRegion;

    @ApiModelProperty(value="登录结果（成功或失败）",required=false)
    private String loginResult;

    @ApiModelProperty(value="登录结果（0成功或1失败）",required=false)
    private String loginStatus;

    @ApiModelProperty(value="失败原因（成功、密码不正确等）",required=false)
    private String failureReason;


    @Column(name = "login_date")
    @ApiModelProperty(value="时间",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime loginDate;

    public UserLoginLog() {
        super();
    }

    public UserLoginLog(String mobile, String ip, String loginResult, String failureReason,ZonedDateTime loginDate,String ipRegion,String loginStatus) {
        super();
        this.mobile = mobile;
        this.ip = ip;
        this.loginResult = loginResult;
        this.failureReason = failureReason;
        this.loginDate = loginDate;
        this.ipRegion = ipRegion;
        this.loginStatus = loginStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public ZonedDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(ZonedDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getIpRegion() {
        return ipRegion;
    }

    public void setIpRegion(String ipRegion) {
        this.ipRegion = ipRegion;
    }

    @Override
    public String toString() {
        return "MemberLoginLog [id=" + id + ", mobile=" + mobile + ", ip=" + ip + ", loginResult=" + loginResult
                + ", failureReason=" + failureReason + "]";
    }
}
