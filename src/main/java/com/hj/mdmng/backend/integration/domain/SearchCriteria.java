package com.hj.mdmng.backend.integration.domain;

import com.hj.mdmng.backend.integration.dao.Parameter;
import lombok.Getter;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hj.mdmng.backend.integration.dao.Parameter.*;

/**
 * Created by heiko on 07.03.15.
 */

@Getter
public abstract class SearchCriteria {

    private MdmTable table;

    private List<Restriction> restrictions = new ArrayList<>();

    public SearchCriteria(MdmTable table) {
        this.table = table;
    }

    public SearchCriteria add(Restriction restriction) {
        checkRestriction(restriction);
        this.restrictions.add(restriction);
        return this;
    }

    private void checkRestriction(Restriction r) {
        if(!table.hasColumn(r.getMetaColumn())) {
            throw new IllegalArgumentException("Column not Member of table");
        }
    }

    public Parameter[] getValuesAsParamters() {
        List<Parameter> parameters = new ArrayList<>();
        for(Restriction restriction : restrictions) {
            for(Object value : restriction.getValues()) {
                parameters.add(parameter(restriction.getMetaColumn().getName(), value));
            }

        }

        return  parameters.toArray(new Parameter[0]);
    }


    protected void checkColumnBelongsToTable(MdmColumn column) {
        if(!table.hasColumn(column)) {
            throw new IllegalArgumentException("column " + column.getName() + " does not belong to table " + table.getName());
        }
    }

    public abstract Equals equals(MdmColumn column, Object value);

    public abstract Like like(MdmColumn column,String value);






}
