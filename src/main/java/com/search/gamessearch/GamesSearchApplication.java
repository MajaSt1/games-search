package com.search.gamessearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Implement client for use
 * front controller servlet - servlet dyspozytora (MVC)
 * przekierowywanie żadania do pozostałych komponentów
 */
@SpringBootApplication
public class GamesSearchApplication //extends SpringBootServletInitializer//implements CommandLineRunner
{

    public static void main(String[] args) {
        SpringApplication.run(GamesSearchApplication.class, args);
    }
   /* @Transactional
    @Override
    public void run(String... arg0) throws Exception {
    } */

 //   @Override
  //  protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
   //     return application.sources(GamesSearchApplication.class);
   // }
}

