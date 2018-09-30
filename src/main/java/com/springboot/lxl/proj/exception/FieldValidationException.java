package main.java.com.springboot.lxl.proj.exception;


/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
public class FieldValidationException extends main.java.com.springboot.lxl.proj.exception.ServiceException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 错误的属性名
     */
    private final String field;


    /**
     * 构造方法
     *
     * @param field
     *            错误的字段名称
     *
     * @param message
     *            错误提示信息
     */
    public FieldValidationException(String field, String message) {
        super(message);
        this.field = field;
    }

    public FieldValidationException(String field, String message, Object data) {
        super(message, data);
        this.field = field;
    }
    public FieldValidationException(String message) {
        super(message);
        this.field = "";
    }

    public String getField() {
        return field;
    }
}
