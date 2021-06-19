package com.ibm.project.service.container.configuration;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> customErrorAttributes = new HashMap<>();
        customErrorAttributes.put("code", errorAttributes.get("status"));
        customErrorAttributes.put("type", errorAttributes.get("error"));
        customErrorAttributes.put("message", errorAttributes.get("message"));
        return customErrorAttributes;
    }
}