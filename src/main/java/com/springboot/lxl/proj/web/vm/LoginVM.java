package com.springboot.lxl.proj.web.vm;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
public class LoginVM {

    private Long id;

    private String nickName;    // 昵称

    private String mobile;      // 手机号

    private String token;

    public LoginVM() {
        super();
    }

    public LoginVM(Long id, String nickName, String mobile) {
        super();
        this.id = id;
        this.nickName = nickName;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return StringUtils.isNotBlank(nickName) ? nickName : "用户"+id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
