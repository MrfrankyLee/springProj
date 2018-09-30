package main.java.com.springboot.lxl.proj.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
@ApiModel(value = "请求响应对象")
public class RestResult<T> {
    @ApiModelProperty(value="请求是否成功")
    private Boolean success;

    @ApiModelProperty(value="错误信息")
    private String errormsg;

    @ApiModelProperty(value="返回数据")
    private T data;

    private RestResult() {
    }

    public static <T> RestResult<T> newInstance() {
        return new RestResult<>();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
