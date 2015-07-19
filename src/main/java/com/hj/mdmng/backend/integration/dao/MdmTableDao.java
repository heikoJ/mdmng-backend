package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.Tag;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.terracotta.context.query.QueryBuilder;

import java.util.List;

/**
 * Created by heiko on 01.03.15.
 */
@Repository
public class MdmTableDao extends AbstractDao<MdmTable> {

    public MdmTableDao() {
        super(MdmTable.class);
    }

    public MdmTable findByTableName(String tableName) {
        MdmTable example = new MdmTable();
        example.setName(tableName);
        List<MdmTable> result = createCriteria().
                add(Example.create(example)).
                list();

        return result.isEmpty() ? null : result.get(0);

    }


    public List<MdmTable> searchByTag(List<String> tags) {

      return  createCriteria()
                .createCriteria("tags")
                .add( Restrictions.in ("name", tags) )
                .list();
    }


}
