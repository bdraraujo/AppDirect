package com.acme.rest.types;

/**
 * Created by bdraraujo on 16-04-12.
 */
public class NotifyResponse {
    private boolean success;
    private String errorCode;
    private String message;
    private String accountIdentifier;

    public NotifyResponse(boolean success, String errorCode, String message, String accountIdentifier) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.accountIdentifier = accountIdentifier;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }
}

