package main.java.com.springboot.lxl.proj.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lixiaole
 * @date 2018/9/30
 * @description
 */
public class ValidateUtils {
    /** 邮箱 */
    public static final String EMAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    /** 用户名 */
    public static final String USERNAME = "^[A-Za-z\u4e00-\u9fa5][\u4e00-\u9fa5\\da-zA-Z_]+$";

    /** 手机 */
    public static final String MOBILE = "^(13|14|15|18|17)[0-9]{9}";

    /** 真实姓名 */
    public static final String REAL_NAME = "^[\u4e00-\u9fa5]{2,6}$";

    /** 银行号码 */
    public static final String BANK_ACCOUNT = "^\\d{16,23}";
    /**
     * 身份证
     */
    public static final String ID_CARD = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";

    private ValidateUtils(){

    }

    /**
     * 验证文本是否能够通过正则表达式
     *
     * @param regex
     *            正则
     *
     * @param text
     *            文本
     *
     * @return 通过返回true，否则返回false
     */
    public static final boolean isValidRegex(String regex, String text) {
        if (StringUtils.isBlank(text))
            return false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    /**
     * 是否用户名
     *
     * @param val
     *            值
     *
     * @return 真/假
     */
    public static final boolean isUsername(String val) {
        if (ValidateUtils.isValidRegex(ValidateUtils.USERNAME, val)) {
            int len = size(val);
            if (len >= 4 && len <= 15) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否邮箱号码
     *
     * @param val
     *            值
     *
     * @return 真/假
     */
    public static final boolean isEmail(String val) {
        return ValidateUtils.isValidRegex(ValidateUtils.EMAIL, val);
    }

    /**
     * 是否手机号码
     *
     * @param val
     *            值
     *
     * @return 真/假
     */
    public static final boolean isMobile(String val) {
        return ValidateUtils.isValidRegex(ValidateUtils.MOBILE, val);
    }

    /**
     * 是否真实姓名
     *
     * @param val
     *            值
     *
     * @return 真/假
     */
    public static final boolean isRealName(String text) {
        if (StringUtils.isBlank(text))
            return false;
        return ValidateUtils.isValidRegex(ValidateUtils.REAL_NAME, text);
    }

    /**
     * 是否真实姓名
     *
     * @param val
     *            值
     * @return 真/假
     */
    public static final boolean isRealIdCard(String text) {
        if (StringUtils.isBlank(text))
            return false;
        return ValidateUtils.isValidRegex(ValidateUtils.ID_CARD, text);
    }

    /**
     * 是否银行账号
     *
     * @param val
     *            值
     *
     * @return 真/假
     */
    public static final boolean isBankAccount(String text) {
        if (StringUtils.isBlank(text))
            return false;
        return ValidateUtils.isValidRegex(ValidateUtils.BANK_ACCOUNT, text);
    }

    public static int size(String text) {
        return text == null ? 0 : text.replaceAll("/[^\\x00-\\xff]/", "xx").length();
    }
}
