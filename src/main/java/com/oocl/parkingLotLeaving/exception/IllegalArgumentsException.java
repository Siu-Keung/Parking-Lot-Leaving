package com.oocl.parkingLotLeaving.exception;

/**
 * @author Dylan Wei
 * @date 2018-08-23 17:10
 */
public class IllegalArgumentsException extends RuntimeException {

    @Override
    public String getMessage() {
        return "操作禁止！";
    }
}
