package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.DataType;
import com.hj.mdmng.backend.integration.domain.MdmColumn;
import com.hj.mdmng.backend.integration.domain.TableData;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.JDBCType;
import java.sql.SQLType;
import java.sql.Types;

/**
 * Created by heiko on 14.03.15.
 */
public class TableSqlParameterSource implements SqlParameterSource {


    private TableData record;


    public TableSqlParameterSource(TableData record) {
        this.record = record;
    }

    @Override
    public int getSqlType(String paramName) {
        DataType type = record.getMetaTable().getColumnByName(paramName).getDataType();
        switch(type) {
            case INTEGER: return Types.INTEGER;
            case STRING: return Types.VARCHAR;
            case DATE: return Types.DATE;
            default: return TYPE_UNKNOWN;
        }

    }

    @Override
    public boolean hasValue(String paramName) {
        MdmColumn column = record.getMetaTable().getColumnByName(paramName);

        return record.getValue(column)!=null;
    }

    @Override
    public Object getValue(String paramName) throws IllegalArgumentException {
        MdmColumn column = record.getMetaTable().getColumnByName(paramName);
        return record.getValue(column).value();

    }

    @Override
    public String getTypeName(String paramName) {
        return record.getMetaTable().getColumnByName(paramName).getDataType().getType().getTypeName();
    }
}
