package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.DBConfig;
import com.hj.mdmng.backend.integration.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Test
    public void testGetAllPaged() throws Exception {

        MdmTable metaTable = metaTabelDao.findByTableName("COUNTRY");

        Pageable p = new PageRequest(0,5);

        Page<TableData> page = tableDao.findAll(metaTable,p);

        List<TableData> result = page.getContent();

        result.forEach(System.out::println);

        System.out.println("Total number of rows: " + page.getTotalElements());


    }


    @Test
    public void testFindByCriteria() throws Exception {

        MdmTable metaTable = metaTabelDao.findByTableName("COUNTRY");

        System.out.println(metaTable);

        Pageable p = new PageRequest(0,5);

        SearchCriteria crit = new HsqlSearchCriteria(metaTable);
      crit.add(crit.equals(metaTable.getColumnByName("CODE"),"DE"));



        Page<TableData> page = tableDao.findByCriteria(crit,p);

        List<TableData> result = page.getContent();

        result.forEach(System.out::println);

        System.out.println("Total number of rows: " + page.getTotalElements());


    }

    @Test
    public void testInsert() throws Exception {

        MdmTable metaTable = metaTabelDao.findByTableName("COUNTRY");

        TableData record = new TableData(metaTable);

        record.setValue(metaTable.getColumnByName("NAME"),"Insert Test");
        record.setValue(metaTable.getColumnByName("CODE"),"XX");


        Long id = tableDao.insertRecord(record);

        System.out.println(id);

        TableData result = tableDao.findByPrimaryKey(metaTable, id);

        System.out.println(result);

    }


    @Test
    public void testUpdate() throws Exception {

        MdmTable metaTable = metaTabelDao.findByTableName("COUNTRY");

        TableData record = tableDao.findByPrimaryKey(metaTable,1);

        record.setValue(metaTable.getColumnByName("NAME"),"Update Test");

        tableDao.updateRecord(record);

        TableData result = tableDao.findByPrimaryKey(metaTable, 1);

        System.out.println(result);


    }

}