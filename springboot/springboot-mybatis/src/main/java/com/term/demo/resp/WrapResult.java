package com.term.demo.resp;

public class WrapResult {
    private Integer status;
    private String message;
    private Object result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public WrapResult(Object result) {
        this.result = result;
    }

    public static WrapResult resultSuccess(Object result) {
        WrapResult wrapResult = new WrapResult(result);
        wrapResult.setStatus(HttpStatus.SUCCESS);
        wrapResult.setMessage("ok");
        return wrapResult;
    }

    public static WrapResult resultError(Object result) {
        WrapResult wrapResult = new WrapResult(result);
        wrapResult.setStatus(HttpStatus.ERROR);
        wrapResult.setMessage("error");
        return wrapResult;
    }

    @Override
    public String toString() {
        return "WrapResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
