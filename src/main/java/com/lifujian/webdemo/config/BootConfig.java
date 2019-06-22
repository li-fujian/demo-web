package com.lifujian.webdemo.config;

import com.zaxxer.hikari.HikariDataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author itlfj
 * @time 2019/06/22 18:59
 * @description 项目相关配置
 */
@Configuration
@MapperScan("com.lifujian.webdemo.mapper")
@EnableCaching
public class BootConfig {

    public static final Logger logger = LoggerFactory.getLogger(BootConfig.class);

    @Bean
    @ConfigurationProperties("test.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configureViewResolvers(ViewResolverRegistry registry) {
                registry.enableContentNegotiation(new JstlView(), new MappingJackson2JsonView());
                registry.jsp("/WEB-INF/jsp/", ".jsp");
            }

            @Override
            public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
                configurer.enable();
            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                for (HttpMessageConverter<?> converter : converters) {
                    if (converter instanceof StringHttpMessageConverter) {
                        StringHttpMessageConverter stringHttpMessageConverter = (StringHttpMessageConverter) converter;
                        stringHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
                    }
                }
            }
        };
    }

}
