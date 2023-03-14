package com.keccoredump.demo.handlers;

public class ExceptionResponseHandler {
    private int status;
    private String errorMessgae;
    private String errorCode;

    public ExceptionResponseHandler() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessgae() {
        return errorMessgae;
    }

    public void setErrorMessgae(String errorMessgae) {
        this.errorMessgae = errorMessgae;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ExceptionResponseHandler(int status, String errorMessgae, String errorCode) {
        this.status = status;
        this.errorMessgae = errorMessgae;
        this.errorCode = errorCode;
    }
}
