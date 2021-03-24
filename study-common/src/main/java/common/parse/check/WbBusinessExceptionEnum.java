package common.parse.check;

/**
 * @author : Pan Yingting
 * @date : 2020/11/11 8:21 下午
 */
public enum WbBusinessExceptionEnum { /**/


    SYS_EXCEPTION(-1, "请求错误，稍后再试"),
    SUCCESS(0, "成功"),
    FAIL(1, "请求失败"),
    LOCK_FAILED(20, "获取锁失败"),
    ;
    public final int code;
    public final String message;

    WbBusinessExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
