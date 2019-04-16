package com.gsp.app.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class ResponseVo<T> implements Serializable {


    private String code;
    private String message;
    private T result;

    private static final String SUCCESS_VALUE = "200";
    private static final String SUCCESS = "操作成功";

    private static final String ERROR_VALUE = "400";
    private static final String ERROR = "系统异常";

    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:构造方法
     * </p>
     */
    public ResponseVo() {
        super();
    }

    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:构造方法
     * </p>
     * 
     * @param ok
     * @param message
     * @param result
     */
    public ResponseVo(String ok, String message, T result) {
        super();
        this.code = ok;
        this.message = message;
        this.result = result;
    }

    public ResponseVo(String code, T result) {
        this.code = code;
        this.result = result;
    }

    public static <T> ResponseVo<T> ofSuccess(T data) {
        return new ResponseVo<>(SUCCESS_VALUE, SUCCESS, data);
    }

    public static <T> ResponseVo<T> ofSuccess() {
        return new ResponseVo<>(SUCCESS_VALUE, SUCCESS, null);
    }

    public static <T> ResponseVo<T> ofErrorMessage(String message) {
        return new ResponseVo<>(ERROR_VALUE, message, null);
    }

    public static <T> ResponseVo<T> ofErrorMessage() {
        return new ResponseVo<>(ERROR_VALUE, ERROR, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}