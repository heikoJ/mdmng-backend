package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.MdmColumn;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by heiko on 07.03.15.
 */
@Cacheable("sqlStatementCache")
@Component
public class SqlStatementProvider {

    @Autowired
    CriteriaClauseProvider criteriaClauseProvider;

    public String getFindByPrimaryKeyStatement(MdmTable table) {
        MdmColumn primaryKeyColumn = table.getPrimaryKeyOrThrowException();
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT * FROM ").
                append(table.getName()).
                append(" WHERE ").
                append(primaryKeyColumn.getName()).
                append(" = :primaryKey");
        return stmt.toString();
    }

    String getFindAllStatement(MdmTable table) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT * FROM ").
                append(table.getName());

        return stmt.toString();
    }

    String getFindAllPageableStmt(MdmTable table) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT * FROM ").
                append(table.getName()).
                append(" LIMIT :limit OFFSET :offset");

        return stmt.toString();
    }

    String getCountStmt(MdmTable table) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT count(*) FROM ").
                append(table.getName());

        return stmt.toString();
    }

    String getFindByCriteriaPageableStmt(SearchCriteria criteria) {
       StringBuilder stmt = new StringBuilder();
       stmt.append("SELECT * FROM ").
               append(criteria.getTable().getName()).
               append(" WHERE ").
               append(criteriaClauseProvider.getCriteriaClause(criteria)).
               append(" LIMIT :limit OFFSET :offset");

        return stmt.toString();
    }

    String getCountByCriteriaStmt(SearchCriteria criteria) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT count(*) FROM ").
                append(criteria.getTable().getName()).
                append(" WHERE ").append(criteriaClauseProvider.getCriteriaClause(criteria));

        return stmt.toString();
    }

    String getInsertStatement(MdmTable table) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("INSERT INTO ").
                append(table.getName()).
                append("(").
                append(getColumnsAsStringList(table)).
                append(")").
                append(" VALUES (").
                append(getColumnPlaceholdersStringList(table)).
                append(")");


        return stmt.toString();
    }


    String getUpdateStmt(MdmTable table) {
        MdmColumn primaryKeyColumn = table.getPrimaryKeyOrThrowException();
        StringBuilder stmt = new StringBuilder();
        stmt.append("UPDATE ").
                append(table.getName()).
                append(" SET ");
        boolean first = true;
        for(MdmColumn column : table.getColumns()) {
            if(column.isPrimaryKey()) {
                continue;
            }

            if(first) {
                first = false;
            } else {
                stmt.append(",");
            }

            stmt.append(column.getName()).
                    append(" = ").
                    append(":").append(column.getName());

        }

        stmt.append(" WHERE  ").
                append(primaryKeyColumn.getName()).
                append(" = ").
                append(":").append(primaryKeyColumn.getName());

        return stmt.toString();

    }



    private String getColumnsAsStringList(MdmTable table) {
        StringBuilder columnList = new StringBuilder();
        boolean first = true;
        for(MdmColumn column : table.getColumns()) {
            if(column.isPrimaryKey()) {
                continue;
            }
            if(first) {
                first = false;
            } else {
                columnList.append(",");
            }
            columnList.append(column.getName());
        }
        return columnList.toString();
    }


    private String getColumnPlaceholdersStringList(MdmTable table) {
        StringBuilder placeholderList = new StringBuilder();
        boolean first = true;
        for(MdmColumn column : table.getColumns()) {
            if(column.isPrimaryKey()) {
                continue;
            }
            if(first) {
                first = false;
            } else {
                placeholderList.append(",");
            }
            placeholderList.append(":").append(column.getName());
        }
        return placeholderList.toString();
    }



}
