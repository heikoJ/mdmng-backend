package com.hj.mdmng.backend.integration.dao;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heiko on 07.03.15.
 */
@Getter
public class Parameter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parameter.class);

    private String name;
    private Object value;

    private Parameter(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public static Parameter parameter(String name, Object value) {
        return new Parameter(name,value);
    }

    public static Map<String,Object> asMap(Parameter ... parameters) {
        HashMap<String, Object> parameterMap = new HashMap<>();
        for(Parameter parameter : parameters) {
            LOGGER.info("Adding paramter " + parameter.getName() + " -> " + parameter.getValue());
            parameterMap.put(parameter.getName(), parameter.getValue());
        }
        return parameterMap;
    }


}
