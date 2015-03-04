package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.MdmColumn;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heiko on 01.03.15.
 */
@Repository
public class TableDao {

    @Autowired
    NamedParameterJdbcOperations jdbc;

    @Autowired
    SqlService sqlService;



    public TableData findByPrimaryKey(MdmTable table, Object primaryKey) {

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("primaryKey",primaryKey);

        return jdbc.queryForObject(
                sqlService.getFindByPrimaryKeyStatement(table),
                parameters,
                new TableDataMapper(table));

    }

    public List<TableData> findAll(MdmTable table) {
        return jdbc.query(
                sqlService.getFindAllStatement(table),
                new TableDataMapper(table));

    }

}
