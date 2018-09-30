package main.java.com.springboot.lxl.proj.exception;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description Service层公用的Exception, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Object data;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
