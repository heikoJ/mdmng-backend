package com.hj.mdmng.backend.integration.domain;

/**
 * Created by heiko on 08.03.15.
 */
public class HsqlLike extends Like {

    public HsqlLike(MdmColumn metaColumn, String value) {
        super(metaColumn, value);
    }

    @Override
    public String getQueryString() {
        String columnName = metaColumn.getName();
        StringBuilder clause = new StringBuilder();
        clause.append("\"" + columnName + "\"");
        clause.append(" LIKE ");
        clause.append(":").append(columnName);
        return clause.toString();
    }
}
