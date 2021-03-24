package common.parse.check;

import java.util.Collection;
import java.util.Map;

/**
 * @author : Pan Yingting
 * @date : 2020/11/11 8:21 下午
 */
public class CheckUtils {

    /**
     * 检查如果数字不为正数则抛出异常
     *
     * @param num 数字
     * @param msg 异常描述信息
     */
    public static void checkIfNotPositive(Number num, String msg) {
        if (num == null || num.doubleValue() <= 0) {
            throw new WbBusinessException(msg);
        }
    }

    /**
     * 检查如果表达式为 true 则抛出异常
     *
     * @param expression 表达式
     * @param msg        异常描述信息
     */
    public static void checkIfTrue(boolean expression, String msg) {
        if (expression) {
            throw new WbBusinessException(msg);
        }
    }

    public static void checkIfTrue(boolean expression, WbBusinessExceptionEnum exceptionCode) {
        if (expression) {
            throw new WbBusinessException(exceptionCode);
        }
    }

    public static void checkIfTrue(boolean expression, int code, String msg, Object data) {
        if (expression) {
            throw new WbBusinessException(code, msg, data);
        }
    }

    /**
     * 检查如果对象为 null 则抛出异常
     *
     * @param object 对象
     * @param msg    异常描述信息
     */
    public static void checkIfNull(Object object, String msg) {
        if (object == null) {
            throw new WbBusinessException(msg);
        }
    }

    public static void checkIfNull(Object object, WbBusinessExceptionEnum exceptionCode) {
        if (object == null) {
            throw new WbBusinessException(exceptionCode);
        }
    }

    /**
     * 检查如果集合为空则抛出异常
     *
     * @param obj 集合对象
     * @param msg 异常描述信息
     */
    public static void checkIfEmpty(Collection<?> obj, String msg) {
        if (obj == null || obj.isEmpty()) {
            throw new WbBusinessException(msg);
        }
    }

    public static void checkIfEmpty(Collection<?> obj, WbBusinessExceptionEnum exceptionCode) {
        if (obj == null || obj.isEmpty()) {
            throw new WbBusinessException(exceptionCode);
        }
    }

    /**
     * 检查字符串如果为空则抛出异常
     *
     * @param obj 字符串对象
     * @param msg 异常描述信息
     */
    public static void checkIfEmpty(String obj, String msg) {
        if (obj == null || obj.isEmpty()) {
            throw new WbBusinessException(msg);
        }
    }

    public static void checkIfEmpty(String obj, WbBusinessExceptionEnum exceptionCode) {
        if (obj == null || obj.length() == 0) {
            throw new WbBusinessException(exceptionCode);
        }
    }

    public static void checkIfEmpty(Map<?, ?> obj, String msg) {
        if (obj == null || obj.isEmpty()) {
            throw new WbBusinessException(msg);
        }
    }

    /**
     * 集合有多个值时返回错误信息，一般配合 checkIfEmpty 一起使用来判断集合只有一个值；
     *
     * @param obj 集合对象
     * @param msg 异常描述信息
     */
    public static void checkMultiValue(Collection<?> obj, String msg) {
        if (obj != null && obj.size() > 1) {
            throw new WbBusinessException(msg);
        }
    }

    public static void checkMultiValue(Collection<?> obj, WbBusinessExceptionEnum exceptionCode) {
        if (obj != null && obj.size() > 1) {
            throw new WbBusinessException(exceptionCode);
        }
    }
}
