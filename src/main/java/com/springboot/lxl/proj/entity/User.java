package com.springboot.lxl.proj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
@Entity
@Table(name = "tb_user")
@ApiModel(value = "用户实体类")
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value="ID",required=false)
    private Long id;

    @Length(max=20)
    @ApiModelProperty(value="昵称",required=true)
    private String nickName;        // 昵称

    @ApiModelProperty(value="用户名",required=false)
    private String username;        // 用户名

    @ApiModelProperty(value="手机号",required=true)
    private String mobile;          // 手机号码

    @ApiModelProperty(value="密码",required=true)
    private String password;        // 密码

    @ApiModelProperty(value="真实姓名",required=false)
    private String realName;        // 真实姓名

    @ApiModelProperty(value="身份证号码",required=false)
    private String idCard;          // 身份证号码

    @ApiModelProperty(value="注册IP",required=false)
    private String regIp;           // 注册IP

    @ApiModelProperty(value="注册时间",required=false)
    private Date regTime;           // 注册时间

    @ApiModelProperty(value="客户端",required=false)
    private String client;          // 客户端

    @ApiModelProperty(value="头像",required=false)
    private String avatar;   // 头像

    @ApiModelProperty(value="简介",required=false)
    private String introduce;   // 简介

    @ApiModelProperty(value="推荐人",required=false)
    private Long recommendId; // 推荐人

    @ApiModelProperty(value="是否启用",required=false)
    private boolean enabled = true;		// 是否启用

    @ApiModelProperty(value="盐值",required=false)
    private String salt;		// 盐值

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Long getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", regIp='" + regIp + '\'' +
                ", regTime=" + regTime +
                ", client='" + client + '\'' +
                ", avatar='" + avatar + '\'' +
                ", introduce='" + introduce + '\'' +
                ", recommendId=" + recommendId +
                ", enabled=" + enabled +
                ", salt='" + salt + '\'' +
                '}';
    }
}
