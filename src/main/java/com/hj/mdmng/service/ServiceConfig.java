package com.hj.mdmng.service;

import com.hj.mdmng.backend.integration.DBConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by heiko on 02.03.15.
 */
@Configuration
@ComponentScan(basePackages = "com.hj.mdmng.service")
@Import(DBConfig.class)
public class ServiceConfig {
}
