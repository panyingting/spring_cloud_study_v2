package common.parse.check;

/**
 * @author : Pan Yingting
 * @date : 2020/11/11 8:21 下午
 */
public class WbBusinessException extends RuntimeException {

    private Object data = null;
    private int code = 1;

    public WbBusinessException(String msg) {
        super(msg);
    }

    public WbBusinessException(WbBusinessExceptionEnum exceptionCode) {
        super(exceptionCode.message);
        this.code = exceptionCode.code;
    }

    public WbBusinessException(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
