package com.hj.mdmng.rest.controller;

import com.hj.mdmng.backend.integration.dao.TableDao;
import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.rest.ResourceNotFoundException;
import com.hj.mdmng.rest.json.TableResource;
import com.hj.mdmng.service.MetaTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by heiko on 08.03.15.
 */
@RequestMapping("/rest/tables/{name}/search")
@RestController
public class TableSearchController {

    @Autowired
    MetaTableService metaTableService;

    @Autowired
    TableDao tableDao;

    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    public TableResource searchAll(@PathVariable String name,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(required = false) Set<String> fields,
                                   @RequestParam(required = false) Set<String> excludeFields) {
        MdmTable table = metaTableService.getTableByName(name).
                orElseThrow(() -> new ResourceNotFoundException("Table",name));

        Pageable pageable = new PageRequest(page,size);


        return new TableResource(table,tableDao.findAll(table,pageable),fields,excludeFields);
    }


}
