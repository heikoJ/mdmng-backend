package com.hj.mdmng.backend.integration.domain;

import java.util.Date;

/**
 * Created by heiko on 01.03.15.
 */
public enum DataType {

    STRING(String.class),
    INTEGER(Integer.class),
    DATE(Date.class);

    private Class type;

    DataType(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }



}
