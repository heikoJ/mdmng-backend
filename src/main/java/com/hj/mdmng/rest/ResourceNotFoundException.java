package com.hj.mdmng.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by heiko on 19.07.15.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The resource was not found")
public class ResourceNotFoundException extends RestException {



    public ResourceNotFoundException(String resourceName, String id) {
        super("The resource " + resourceName + " was not found for identifier " + id);
    }

}
