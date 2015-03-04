package com.hj.mdmng.rest.controller;

import com.hj.mdmng.backend.integration.domain.MdmTable;
import com.hj.mdmng.service.MetaTableService;
import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by heiko on 02.03.15.
 */
@RestController
@RequestMapping("/rest/tables")
@Api(value="",description="Provides table metadata")
public class MetaTableController {

    @Autowired
    MetaTableService metaTableService;

    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    public List<MdmTable> tables() {
        return metaTableService.getAllTables();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET,produces = "application/json")
    public ResponseEntity<MdmTable> tableById(@PathVariable String id) {
        MdmTable table = metaTableService.getTableByPrimaryKey(Long.valueOf(id));


        return new ResponseEntity<>(table,getStatusOkOrNotFoundIfNull(table));
    }


    HttpStatus getStatusOkOrNotFoundIfNull(Object o) {
        return o==null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    }

}
