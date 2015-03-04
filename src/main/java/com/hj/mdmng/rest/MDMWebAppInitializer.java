package com.hj.mdmng.rest;

import com.hj.mdmng.backend.integration.DBConfig;
import com.hj.mdmng.rest.RestConfig;
import com.hj.mdmng.service.ServiceConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by heiko on 02.03.15.
 */
public class MDMWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ServiceConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {RestConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("INIT");

        return new String[] {"/"};
    }
}
