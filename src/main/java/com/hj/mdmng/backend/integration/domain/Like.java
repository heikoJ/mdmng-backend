package com.hj.mdmng.backend.integration.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heiko on 08.03.15.
 */
public abstract class Like extends AbstractRestriction implements Restriction {

    private String value;

    public Like(MdmColumn metaColumn, String value) {
        super(metaColumn);
        this.value = value;
    }

    @Override
    public List<Object> getValues() {
        ArrayList<Object> values = new ArrayList<>();
        values.add(value);

        return values;
    }



}
