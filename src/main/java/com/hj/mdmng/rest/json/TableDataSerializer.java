package com.hj.mdmng.rest.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hj.mdmng.backend.integration.domain.MdmColumn;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.TableData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heiko on 08.03.15.
 */
public class TableDataSerializer extends JsonSerializer<TableResource> {


    @Override
    public void serialize(TableResource tableData, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        List<MdmColumn> columns = getVisibleColumns(tableData);
        jgen.writeStartObject();
        jgen.writeObjectFieldStart("page");
        jgen.writeFieldName("number");
        jgen.writeNumber(tableData.getPage().getNumber());
        jgen.writeFieldName("size");
        jgen.writeNumber(tableData.getPage().getSize());


        jgen.writeArrayFieldStart("data");
        for(TableData data : tableData.getPage()) {
            jgen.writeStartObject();
            for (MdmColumn column : columns) {
                jgen.writeStringField(column.getName(), data.getValue(column).toString());
            }
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
        jgen.writeEndObject();
        jgen.writeEndObject();
    }


    private List<MdmColumn> getVisibleColumns(TableResource tableData) {
        MdmTable metaTable = tableData.getMetaTable();
        List<MdmColumn> columns = new ArrayList<>();
        for(MdmColumn column : metaTable.getColumns()) {
            if(tableData.isFieldVisible(column)) {
                columns.add(column);
            }
        }
        return columns;
    }

}
