package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.MdmColumn;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.SearchCriteria;
import com.hj.mdmng.backend.integration.domain.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private SqlService sqlService;



    public TableData findByPrimaryKey(MdmTable table, Object primaryKey) {
        return sqlService.findByPrimaryKey(table,primaryKey);

    }

    public Long insertRecord(TableData data) {
        return sqlService.insertRecord(data);
    }

    public List<TableData> findAll(MdmTable table) {
        return sqlService.findAll(table);
    }

    public Page<TableData> findAll(MdmTable table, Pageable pageable) {
        return sqlService.findAll(table,pageable);
    }

    public Page<TableData> findByCriteria(SearchCriteria criteria,Pageable pageable) {
        return sqlService.findByCriteria(criteria,pageable);
    }

    public void updateRecord(TableData record) {
        sqlService.updateRecord(record);
    }

}
