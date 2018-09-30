package main.java.com.springboot.lxl.proj.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.springboot.lxl.proj.common.*;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
public class RestUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestUtils.class);

    /**
     * normal
     *
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> main.java.com.springboot.lxl.proj.common.RestResult<T> result(boolean success, T data, String message) {
        main.java.com.springboot.lxl.proj.common.RestResult<T> result = main.java.com.springboot.lxl.proj.common.RestResult.newInstance();
        result.setSuccess(success);
        result.setData(data);
        result.setErrormsg(message);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:{}", result);
        }
        return result;
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> main.java.com.springboot.lxl.proj.common.RestResult<T> ok(T data) {

        return result(true, data, null);
    }

    /**
     * error message
     *
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> main.java.com.springboot.lxl.proj.common.RestResult<T> error(String message) {

        return result(false, null, message);
    }

    /**
     * error
     *
     * @param error
     * @param <T>
     * @return
     */
    public static <T> main.java.com.springboot.lxl.proj.common.RestResult<T> error(Exception error) {

        return error(error.getMessage());
    }

    /**
     * success no message
     *
     * @return
     */
    public static main.java.com.springboot.lxl.proj.common.RestResult ok() {
        return ok(null);
    }
}
