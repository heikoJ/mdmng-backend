package com.hj.mdmng.backend.integration.domain;

import java.util.List;

/**
 * Created by heiko on 07.03.15.
 */
public interface Restriction {

    public MdmColumn getMetaColumn();

    public List<Object> getValues();

    public String getQueryString();
}
