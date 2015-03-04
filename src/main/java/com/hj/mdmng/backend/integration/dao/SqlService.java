package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.MdmTable;
import org.springframework.stereotype.Component;

/**
 * Created by heiko on 01.03.15.
 */
@Component
public class SqlService {




    public String getFindByPrimaryKeyStatement(MdmTable table){
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT * FROM ").
                append(table.getName()).
                append(" WHERE ").
                append(table.getPrimaryKeyColumn().getName()).
                append(" = :primaryKey");

        return stmt.toString();
    }

    public String getFindAllStatement(MdmTable table) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT * FROM ").
                append(table.getName());

        return stmt.toString();

    }

}
