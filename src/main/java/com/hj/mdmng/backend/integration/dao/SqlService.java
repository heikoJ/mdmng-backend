package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.SearchCriteria;
import com.hj.mdmng.backend.integration.domain.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hj.mdmng.backend.integration.dao.Parameter.*;

/**
 * Created by heiko on 01.03.15.
 */
@Component
public class SqlService {


    @Autowired
    NamedParameterJdbcOperations jdbc;

    @Autowired
    SqlStatementProvider statementProvider;


    public TableData findByPrimaryKey(MdmTable table, Object primaryKey){
        String stmt = statementProvider.getFindByPrimaryKeyStatement(table);


        return jdbc.queryForObject(
                stmt,
                asMap(parameter("primaryKey",primaryKey)),
                new TableDataMapper(table));

    }

    public List<TableData> findAll(MdmTable table) {
        String stmt = statementProvider.getFindAllStatement(table);

        return jdbc.query(stmt,
                new TableDataMapper(table));

    }

    Page<TableData> findAll(MdmTable table,Pageable pageable) {
        String stmt = statementProvider.getFindAllPageableStmt(table);

        long count = getCount(table);

        List<TableData> result = Collections.emptyList();

        if(count > 0) {
            result = jdbc.query(stmt,
                    asMap(parameter("limit", pageable.getPageSize()),
                            parameter("offset", pageable.getOffset())),
                    new TableDataMapper(table));
        }


        return new PageImpl<>(result,pageable, count);

    }


    public long getCount(MdmTable table) {
        Long count = jdbc.queryForObject(statementProvider.getCountStmt(table), new HashMap<String, Object>(),Long.class);
        if(count==null) return 0;
        return count;
    }

    public Long getCountByCriteria(SearchCriteria criteria) {
        Map<String,Object> parameterMap = asMap(criteria.getValuesAsParamters());
        Long count =
                jdbc.queryForObject(statementProvider.getCountByCriteriaStmt(criteria),parameterMap, Long.class );
        if(count==null) return null;
        return count;
    }

    public Page<TableData> findByCriteria(SearchCriteria criteria, Pageable pageable) {
        String stmt = statementProvider.getFindByCriteriaPageableStmt(criteria);

        long count = getCountByCriteria(criteria);
        List<TableData> result = Collections.emptyList();

        if(count > 0) {

            Map<String,Object> parameterMap = asMap(criteria.getValuesAsParamters());
            parameterMap.putAll(asMap(parameter("limit", pageable.getPageSize()),
                    parameter("offset", pageable.getOffset())));

            result = jdbc.query(stmt,
                    parameterMap,
                    new TableDataMapper(criteria.getTable()));
        }

        return new PageImpl<>(result,pageable,count);

    }

    public Long insertRecord(TableData record) {
        String stmt = statementProvider.getInsertStatement(record.getMetaTable());



        jdbc.update(stmt,new TableSqlParameterSource(record));
        return jdbc.queryForObject("call IDENTITY()",(Map)null,Long.class);

    }


    public void updateRecord(TableData record) {
        String stmt = statementProvider.getUpdateStmt(record.getMetaTable());

        jdbc.update(stmt,new TableSqlParameterSource(record));
    }





}
