package com.hj.mdmng.backend.integration.domain;

/**
 * Created by heiko on 08.03.15.
 */
public class HsqlEquals extends Equals {

    public HsqlEquals(MdmColumn column, Object value) {
        super(column, value);
    }

    @Override
    public String getQueryString() {
        String columnName = metaColumn.getName();
        StringBuilder clause = new StringBuilder();
        clause.append(columnName);
        clause.append(" = ");
        clause.append(":").append(columnName);
        return clause.toString();
    }
}
