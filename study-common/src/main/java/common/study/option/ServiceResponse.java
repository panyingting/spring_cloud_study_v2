package common.study.option;

import java.io.Serializable;

public class ServiceResponse<T> implements Serializable {
    private static final long serialVersionUID = -7105469190103583078L;
    public static final int SUCCESS_KEY = 0;
    public static final int FAIL_KEY = 1;
    public static final ServiceResponse FAIL_RESPONSE = createFailResponse(1, (String) null);
    public static final ServiceResponse SUCCESS_RESPONSE = createSuccessResponse((Object) null);
    public static final ServiceResponse FAIL_NOT_FOUND_RESPONSE = createFailResponse(404, "未找到数据");
    public static final ServiceResponse FAIL_EXISTED_RESPONSE = createFailResponse(604, "已经存在");
    public static final ServiceResponse PARAM_ERROR_RESPONSE = createFailResponse(400, "参数错误");
    public static final ServiceResponse STATUS_ERROR_RESPONSE = createFailResponse(400, "状态错误");
    private boolean success;
    private int code;
    private T data;
    private String msg;

    public ServiceResponse(boolean success, int code, T data, String msg) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ServiceResponse() {
        this.success = false;
        this.code = 0;
        this.data = null;
        this.msg = null;
    }

    public T getData() {
        return this.data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean isFailure() {
        return !this.success;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean isNotFound() {
        return this.code == 404;
    }

    public static <T> ServiceResponse<T> createSuccessResponse(T data) {
        return new ServiceResponse(true, 0, data, (String) null);
    }

    public static <T> ServiceResponse<T> createSuccessResponse(T data, String msg) {
        return new ServiceResponse(true, 0, data, msg);
    }

    public static <T> ServiceResponse<T> createFailResponse(int code, String msg) {
        return new ServiceResponse(false, code, (Object) null, msg);
    }

    public static <T> ServiceResponse<T> createFailResponse(String msg) {
        return new ServiceResponse(false, 1, (Object) null, msg);
    }

    public static <T> ServiceResponse<T> createFailResponse(int code, T data, String msg) {
        return new ServiceResponse(false, code, data, msg);
    }

    public static <T> ServiceResponse<T> defaultFailResponse() {
        return FAIL_RESPONSE;
    }

    public static <T> ServiceResponse<T> failResponse() {
        return FAIL_RESPONSE;
    }

    public static <T> ServiceResponse<T> succResponse() {
        return SUCCESS_RESPONSE;
    }

    public static <T> ServiceResponse<T> notFoundResponse() {
        return FAIL_NOT_FOUND_RESPONSE;
    }

    public static <T> ServiceResponse<T> paramErrorResponse() {
        return PARAM_ERROR_RESPONSE;
    }

    public static <T> ServiceResponse<T> statusErrorResponse() {
        return STATUS_ERROR_RESPONSE;
    }

    public static <T> ServiceResponse<T> existedErrorResponse() {
        return FAIL_EXISTED_RESPONSE;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

