package com.search.gamessearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = {"com.search.gamessearch.controller"})
public class MvcWebConfig extends WebMvcConfigurerAdapter {
   @Autowired
    private ApplicationContext applicationContext;

   @Bean
    public SpringResourceTemplateResolver templateResolver(){
       SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
       templateResolver.setApplicationContext(applicationContext);
       templateResolver.setPrefix("classpath:/templates/");
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
    //Register ThymeleafViewResolver
   @Override
   public void configureViewResolvers(ViewResolverRegistry registry) {
      ThymeleafViewResolver viewResolver= new ThymeleafViewResolver();
      viewResolver.setTemplateEngine(templateEngine());
      registry.viewResolver(viewResolver);
   }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/css/**")
                .addResourceLocations(
                        "classpath:/static/css/"
                );
    }
}
