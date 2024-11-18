package com.project.project.utils.response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response extends AbstractResponse{

    // Success Response Overloaded
    public static ResponseEntity<Object> success() {
        return success(true, "success", null, null);
    }
    public static ResponseEntity<Object> success(Object data) {
        return success(true, "success", data, null);
    }
    public static ResponseEntity<Object> success(String message) {
        return success(true, message, null, null);
    }
    public static ResponseEntity<Object> success(String message, Object data) {
        return success(true, message, data, null);
    }
    public static ResponseEntity<Object> success(String message, HttpStatus status) {
        return success(true, message, null, status);
    }
    public static ResponseEntity<Object> success(HttpStatus status) {
        return success(true, "success", null, status);
    }
    public static ResponseEntity<Object> success(Object data, HttpStatus status) {
        return success(true, "success", data, status);
    }
    

    // Error Response Overloaded
    public static ResponseEntity<Object> error(){
        return error(false,null, null);
    }
    public static ResponseEntity<Object> error(String message){
        return error(false,message, HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity<Object> error(String message, HttpStatus status){
        return error(false, message, status);
    }

    
}
