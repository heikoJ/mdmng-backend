package com.hj.mdmng.backend.integration.domain;



/**
 * Created by heiko on 01.03.15.
 */
public class Value<T> {

    T value;

    public Value(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
