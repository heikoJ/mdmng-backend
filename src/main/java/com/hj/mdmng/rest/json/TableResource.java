package com.hj.mdmng.rest.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hj.mdmng.backend.integration.domain.MdmColumn;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.TableData;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by heiko on 28.06.15.
 */
//@JsonSerialize(using = TableDataSerializer.class)
@Data
public class TableResource extends ResourceSupport {

    private Page<TableData> page;

    private MdmTable metaTable;

    private Set<String> fields = new HashSet<>();
    private Set<String> excludeFields = new HashSet<>();

    public TableResource(MdmTable metaTable, Page<TableData> page, Set<String> fields, Set<String> excludeFields) {
        if(fields!=null) this.fields.addAll(fields);
        if(excludeFields!=null) this.excludeFields.addAll(excludeFields);
        this.metaTable = metaTable;
        this.page = page;
    }


    public boolean isFieldVisible(MdmColumn column) {
        return isInFieldList(column.getName()) && ! isInExcludeFieldList(column.getName());
    }

    private boolean isInFieldList(String columnName) {
        return fields.isEmpty() || fields.contains(columnName);
    }

    private boolean isInExcludeFieldList(String columnName) {
        return excludeFields.contains(columnName);
    }




}
