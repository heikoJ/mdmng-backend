package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.DataType;
import com.hj.mdmng.backend.integration.domain.MdmColumn;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.TableData;
import org.springframework.jdbc.core.RowMapper;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by heiko on 01.03.15.
 */
public class TableDataMapper implements RowMapper<TableData> {

    private MdmTable table;

    public TableDataMapper(MdmTable table) {
        this.table = table;
    }


    @Override
    public TableData mapRow(ResultSet rs, int rowNum) throws SQLException {
        TableData data = new TableData(table);
        for(MdmColumn column : table.getColumns() ) {
            mapColumnValue(data,column,rs,rowNum);
        }

        return data;
    }

    private void mapColumnValue(TableData data,MdmColumn column,ResultSet rs, int rowNum) throws SQLException{
        data.setValue(column,getValueFromResultSet(rs,column.getName(),column.getDataType()));
    }

    private Object getValueFromResultSet(ResultSet rs,String columnName,DataType dataType) throws SQLException {
        switch(dataType) {
            case INTEGER: {
                int i = rs.getInt(columnName);
                if(rs.wasNull()) return null;
                return new Integer(i);
            }
            case STRING: {
                return rs.getString(columnName);
            }
            default: {
                throw new InvalidParameterException("unknown Type!");
            }
        }
    }


}
