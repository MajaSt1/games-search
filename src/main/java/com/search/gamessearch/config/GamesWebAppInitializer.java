package com.search.gamessearch.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * implements WebApplicationInitializer
 */

public class GamesWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings(){
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses(){
        return new Class<?>[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses(){
        return new Class<?>[]{ MvcWebConfig.class};
    }
}
