package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.DBConfig;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.TableData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DBConfig.class)
@Transactional
public class TableDaoTest {


    @Autowired
    TableDao tableDao;

    @Autowired
    MdmTableDao metaTabelDao;

    @Test
    public void testGetByPrimaryKey() throws Exception {

        MdmTable metaTable = metaTabelDao.findByTableName("COUNTRY");

        TableData result = tableDao.findByPrimaryKey(metaTable, 1);

         System.out.println(result);


    }
}