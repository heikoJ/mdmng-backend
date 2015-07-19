package com.hj.mdmng.backend.integration.domain;

/**
 * Created by heiko on 08.03.15.
 */
public class HsqlSearchCriteria extends SearchCriteria {

    public HsqlSearchCriteria(MdmTable table) {
        super(table);
    }

    @Override
    public Equals equals(MdmColumn column, Object value) {
        checkColumnBelongsToTable(column);
        return new HsqlEquals(column, value);
    }

    @Override
    public Like like(MdmColumn column, String value) {
        checkColumnBelongsToTable(column);
        return new HsqlLike(column,value);
    }
}
