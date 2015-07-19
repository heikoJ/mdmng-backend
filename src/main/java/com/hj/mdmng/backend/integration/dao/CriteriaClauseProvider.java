package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.Equals;
import com.hj.mdmng.backend.integration.domain.MdmColumn;
import com.hj.mdmng.backend.integration.domain.Restriction;
import com.hj.mdmng.backend.integration.domain.SearchCriteria;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by heiko on 07.03.15.
 */
@Component
public class CriteriaClauseProvider {


    public String getCriteriaClause(SearchCriteria criteria) {

        StringBuilder clause = new StringBuilder();

        boolean first = true;
        for(Restriction restriction : criteria.getRestrictions()) {
            if(first) {
               first = false;
            } else {
                clause.append(" AND ");
            }
            clause.append(restriction.getQueryString());
        }

        return clause.toString();
    }



}
