package com.hj.mdmng.backend.integration.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heiko on 07.03.15.
 */
@Getter
public abstract class Equals extends AbstractRestriction implements Restriction {

    private Object value;

    public Equals(MdmColumn column, Object value) {
        super(column);
        this.value=value;
        checkValue();
    }


    @Override
    public List<Object> getValues() {
        ArrayList<Object> values = new ArrayList<>();
        values.add(value);

        return values;
    }

    private void checkValue() throws IllegalArgumentException {
        if(value!=null && !(metaColumn.getDataType().getType().isInstance(value))) {
            throw new IllegalArgumentException("wrong type!");
        }
    }

}
