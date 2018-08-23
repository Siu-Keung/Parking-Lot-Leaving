package com.oocl.parkingLotLeaving.exception;

/**
 * @author Dylan Wei
 * @date 2018-08-23 17:10
 */
public class IllegalArgumentsException extends RuntimeException {
    private String message = "操作禁止";

    public IllegalArgumentsException(){

    }

    public IllegalArgumentsException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
