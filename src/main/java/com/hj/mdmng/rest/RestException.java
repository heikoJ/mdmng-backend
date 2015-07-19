package com.hj.mdmng.rest;

/**
 * Created by heiko on 19.07.15.
 */
public class RestException extends RuntimeException {

    public RestException(String message) {
        super(message);
    }
}
