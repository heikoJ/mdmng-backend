package com.hj.mdmng.service;

import com.hj.mdmng.backend.integration.dao.MdmTableDao;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by heiko on 02.03.15.
 */
@Service
@Transactional
public class MetaTableService {

    @Autowired
    MdmTableDao metaTableDao;



    public List<MdmTable> getAllTables()
    {
        List<MdmTable> tables = metaTableDao.findAll();
        tables.forEach( System.out::println);
        return tables;
    }

    public Optional<MdmTable> getTableByName(String name) {
        return Optional.ofNullable(metaTableDao.findByTableName(name));
    }

    public MdmTable getTableByPrimaryKey(Long id) {
        return metaTableDao.findOne(id);
    }

}
