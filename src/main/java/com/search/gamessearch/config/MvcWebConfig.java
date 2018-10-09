package com.search.gamessearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.search.gamessearch.controller")
public class MvcWebConfig implements WebMvcConfigurer {
   @Autowired
    private ApplicationContext applicationContext;

   @Bean
    public SpringResourceTemplateResolver templateResolver(){
       SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
       templateResolver.setApplicationContext(applicationContext);
       templateResolver.setPrefix("templates/");
       templateResolver.setSuffix(".html");
       templateResolver.setTemplateMode("HTML5");
       return templateResolver;
   }
   @Bean
    public SpringTemplateEngine templateEngine(){
       SpringTemplateEngine templateEngine= new SpringTemplateEngine();
       templateEngine.setTemplateResolver(templateResolver());
       return templateEngine;
   }

   @Override
   public void configureViewResolvers(ViewResolverRegistry registry) {
      ThymeleafViewResolver viewResolver= new ThymeleafViewResolver();
      viewResolver.setTemplateEngine(templateEngine());
      registry.viewResolver(viewResolver);
   }
}
