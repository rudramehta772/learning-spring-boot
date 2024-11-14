package com.project.project.utils.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractResponse {
    protected static ResponseEntity<Object> buildResponse(boolean success, String message, Object data, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", (message != null && !message.isEmpty()) ? message : (success ? "success" : "error"));
        if (data != null) response.put("data", data);
        return new ResponseEntity<>(response, status != null ? status : (success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR));
    }

    // Abstract method to be implemented by subclasses
    public abstract ResponseEntity<Object> response();
}
