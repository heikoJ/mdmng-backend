package com.hj.mdmng.backend.integration.domain;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hj.mdmng.rest.json.TableDataSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heiko on 01.03.15.
 */

public class TableData {

    public TableData(MdmTable table) {
        this.table = table;
    }

    private MdmTable table;
    private Map<MdmColumn,Value<?>> values = new HashMap<>();


    public void setValue(MdmColumn column,Object value) {
        if(!table.getColumns().contains(column)) {
            throw new IllegalArgumentException("Column is not a member of table " + table.getName() );
        }
        values.put(column, new Value<>(value));
    }

    public Value<?> getValue(MdmColumn column) {
        return values.get(column);
    }

    public Value<?> getPrimaryKeyValue() {
        return getValue(table.getPrimaryKeyOrThrowException());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(table.getName()).append(", ID=").append(getPrimaryKeyValue());
        sb.append("\n");
        for(MdmColumn column : table.getColumns()) {
            if(column.isPrimaryKey()) continue;
            sb.append(column.getName()).append(" = ").append(getValue(column)).append("\n");
        }

        return sb.toString();
    }


    public MdmTable getMetaTable() {
        return table;
    }

}
