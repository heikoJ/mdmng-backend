package com.hj.mdmng.backend.integration.dao;

import com.hj.mdmng.backend.integration.domain.AbstractDomainObject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by heiko on 01.03.15.
 */
public abstract class AbstractDao<D extends AbstractDomainObject> {

    protected Class<D> clazz;

    protected AbstractDao(Class<D> clazz) {
        this.clazz = clazz;
    }

    @Autowired
    private SessionFactory sessionFactory;


    protected Session session() {
        return sessionFactory.getCurrentSession();
    }


    public long count() {
        return findAll().size();
    }

    public List<D> findAll() {
        return createCriteria().list();
    }

    public D findOne(Long id) {
        return (D)session().get(clazz,id);
    }

    public void save(D domainObject) {
        Serializable id = session().save(domainObject);
    }

    public Criteria createCriteria() {
        Criteria criteria = session().
                createCriteria(clazz);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria;
    }


}
