package com.search.gamessearch;

import com.search.gamessearch.model.Genre;
import com.search.gamessearch.model.User;
import com.search.gamessearch.model.VideoGame;
import com.search.gamessearch.repository.UserRepository;
import com.search.gamessearch.repository.VideoGameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Implement client for use
 * front controller servlet - servlet dyspozytora (MVC)
 */
@SpringBootApplication
public class GamesSearchApplication extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(GamesSearchApplication.class); }

    public static void main(String[] args) {
        SpringApplication.run(GamesSearchApplication.class, args);
    }
    @Bean
    public CommandLineRunner run(VideoGameRepository gamesRepository, UserRepository userRepository) {
        return (args)-> {
            Genre mmo = new Genre("MMO RPG");
            Genre rpg = new Genre("RPG");
            Genre fantasy = new Genre("Fantasy");
            Genre indie = new Genre("Indie Games");

            VideoGame kcd = new VideoGame("Kingdom Come: Deliverance", "It is an action RPG, epic in scale, set in the Medieval period, specifically during the Hussite Wars ");
            VideoGame wow = new VideoGame("World of Warcraft: Battle of Azeroth", "The seventh expansion for World of Warcraft, focusing on the rivalry between the two most important " +
                    "factions of Azeroth, the Alliance and the Horde.");
            VideoGame cup = new VideoGame("Cuphead", "Cuphead is a classic run and gun action game heavily focused on boss battles.");
            VideoGame divinity = new VideoGame("Divinity: Original Sin II", "A direct sequel to Divinity: Original Sin and another installment in " +
                    "the popular RPG series launched in 2002 by the Belgian developer, Larian Studios.");

            mmo.getGames().add(wow);
            rpg.getGames().add(kcd);
            rpg.getGames().add(divinity);
            fantasy.getGames().add(divinity);
            fantasy.getGames().add(wow);
            indie.getGames().add(cup);

            kcd.getGenres().add(rpg);
            wow.getGenres().add(mmo);
            wow.getGenres().add(fantasy);
            cup.getGenres().add(indie);
            divinity.getGenres().add(rpg);
            divinity.getGenres().add(fantasy);

            gamesRepository.save(kcd);
            long kcd_Id = kcd.getId();
            gamesRepository.save(wow);
            long wow_Id = wow.getId();
            gamesRepository.save(cup);
            long cup_Id = cup.getId();
            gamesRepository.save(divinity);
            long divinity_Id = divinity.getId();

            User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6");
            userRepository.save(user1);
        };
    }
}

