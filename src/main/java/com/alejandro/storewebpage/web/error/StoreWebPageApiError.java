package com.alejandro.storewebpage.web.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreWebPageApiError {

    private final Integer status;
    private final String reason;
    private final String message;
    private final List<String> validations;
    private final boolean success;

    public StoreWebPageApiError(Integer status,
                                String reason,
                                String message,
                                List<String> validations) {
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.validations = validations;
        this.success = false;
    }

    public static StoreWebPageApiError createError(Integer status,
                                                   String reason,
                                                   String message,
                                                   List<String> validations) {
        return new StoreWebPageApiError(status, reason, message, validations);
    }

    public static StoreWebPageApiError createError(Integer status,
                                                   String reason,
                                                   String message) {
        return new StoreWebPageApiError(status, reason, message, null);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("reason", reason);
        map.put("message", message);
        map.put("validations", validations);
        map.put("success", success);
        return map;
    }

    public Integer getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public String getMessage() { return message; }

    public List<String> getValidations() {
        return validations;
    }

    public boolean isSuccess() {
        return success;
    }
}
