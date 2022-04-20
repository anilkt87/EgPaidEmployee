package com.egpaid.employeeapp.base.domain;


public class ResponseServiceCode {

    public static final ResponseServiceCode OK_INSTANCE = new ResponseServiceCode(0, "OK");

    public final int code;
    public final String stringCode;
    public final String message;

    public ResponseServiceCode(int code, String message) {
        this.code = code;
        this.stringCode = String.valueOf(code);
        this.message = message;
    }

    public ResponseServiceCode(int code, String stringCode, String message) {
        this.code = code;
        this.stringCode = stringCode;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getStringCode() {
        return stringCode;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public boolean isFailure420() {
        return code == 420;
    }
}
