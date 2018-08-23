package com.oocl.parkingLotLeaving.exception;

/**
 * @author Dylan Wei
 * @date 2018-08-23 19:52
 */
public class ResourceNotFoundException extends RuntimeException {
    private String message = "资源不存在";

    @Override
    public String getMessage() {
        return this.message;
    }
}
