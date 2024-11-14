package com.project.project.utils.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response extends AbstractResponse{

    // Overloaded
    public static ResponseEntity<Object> success() {
        return success(true, "success", null, null);
    }
    public static ResponseEntity<Object> success(Object data) {
        return success(true, "success", data, null);
    }
    public static ResponseEntity<Object> success(String message) {
        return success(true, message, null, null);
    }
    public static ResponseEntity<Object> success(HttpStatus status) {
        return success(true, "success", null, status);
    }
    public static ResponseEntity<Object> success(Object data, HttpStatus status) {
        return success(true, "success", data, status);
    }

    public static ResponseEntity<Object> success(boolean success, String message, Object data, HttpStatus status){
        Map<String, Object>response = new HashMap<>();
        if (data != null) {
            response.put("data", data);
        }

        response.put("success", success);
        //response.put("message", message);

        if (message != null && !message.isEmpty()) {
            response.put("message", message);
        } else{
            response.put("message", "success");
        }

        if (status == null) {
            status = HttpStatus.OK;
        } 

        return new ResponseEntity<>(response, status);

    }
    
    public static ResponseEntity<Object> error(){
        return error(false,null, null);
    }

    public static ResponseEntity<Object> error(String message){
        return error(false,message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> error(boolean success,String message, HttpStatus status){
        Map<String, Object>response = new HashMap<>();
        
        response.put("success", success);

        if (message != null && !message.isEmpty()) {
            response.put("message", message);
        } else{
            response.put("message", "error");
        }

        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, status);

    }
}
