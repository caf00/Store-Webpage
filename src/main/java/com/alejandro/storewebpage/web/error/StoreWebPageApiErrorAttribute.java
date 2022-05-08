package com.alejandro.storewebpage.web.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class StoreWebPageApiErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        StoreWebPageApiError storeWebPageApiError = StoreWebPageApiError.createError(
                (Integer) errorAttributes.get("status"),
                (String) errorAttributes.getOrDefault("error", "No message available"),
                (String) errorAttributes.getOrDefault("message", "No message available")
        );
        return storeWebPageApiError.toMap();
    }
}
