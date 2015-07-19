package com.hj.mdmng.backend.integration.domain;

import lombok.Getter;

/**
 * Created by heiko on 07.03.15.
 */
@Getter
public abstract class AbstractRestriction implements Restriction{

    MdmColumn metaColumn;

    protected AbstractRestriction(MdmColumn metaColumn) {
        this.metaColumn = metaColumn;
    }


}
