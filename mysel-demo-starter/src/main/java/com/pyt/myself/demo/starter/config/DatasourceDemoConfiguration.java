package com.pyt.myself.demo.starter.config;

import com.pyt.myself.demo.starter.DataSource;
import com.pyt.myself.demo.starter.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DataSourceService.class)
@EnableConfigurationProperties(DataSource.class)
@ConditionalOnProperty(prefix = "demo.datasource", value = "enable", matchIfMissing = true)
public class DatasourceDemoConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    @ConditionalOnMissingBean(DataSourceService.class)
    public DataSourceService getDataSourceService(){
        return new DataSourceService(dataSource);
    }
}
