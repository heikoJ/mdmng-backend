package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.DBConfig;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.backend.integration.domain.Tag;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DBConfig.class)
@Transactional
public class MdmTableDaoTest {

    @Autowired
    MdmTableDao tableDao;

    @Test
    public void testFindAll() throws Exception {

        MdmTable test = new MdmTable();
        test.setName("Test");
        test.setDisplayName("Test");
        tableDao.save(test);

        List<MdmTable> result = tableDao.findAll();

        System.out.println("TABLES: -----------------------------------------------");

        System.out.println(result);
        System.out.println(result.size());

        result.forEach(System.out::println);

    }



    @Test
    public void testSearchByTags() throws Exception {

        List<String> tags = new ArrayList<>();

        tags.add("country");
        tags.add("blafalse");

        List<MdmTable> result = tableDao.searchByTag(tags);

        result.forEach(System.out::println);

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals("COUNTRY", result.get(0).getName());



    }
}