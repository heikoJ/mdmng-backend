package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.MdmColumn;
import org.springframework.stereotype.Repository;

/**
 * Created by heiko on 01.03.15.
 */
@Repository
public class MdmColumnDao extends AbstractDao<MdmColumn> {

    public MdmColumnDao() {
        super(MdmColumn.class);
    }

}
