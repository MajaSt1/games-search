package com.search.gamessearch.dao;

import com.search.gamessearch.model.Genre;
import com.search.gamessearch.model.VideoGame;
import com.search.gamessearch.repository.VideoGameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoGameDaoTestSuite {
    @Autowired
    VideoGameRepository gamesRepository;

    @Test
    public void testSaveManyToMany(){
        //Given
        Genre mmo = new Genre("MMO RPG");
        Genre rpg= new Genre("RPG");
        Genre fantasy= new Genre("Fantasy");
        Genre indie= new Genre("Indie Games");

        VideoGame kcd= new VideoGame("Kingdom Come: Deliverance","It is an action RPG, epic in scale, set in the Medieval period, specifically during the Hussite Wars ");
        VideoGame wow= new VideoGame("World of Warcraft: Battle of Azeroth","The seventh expansion for World of Warcraft, focusing on the rivalry between the two most important " +
                "factions of Azeroth, the Alliance and the Horde.");
        VideoGame cup= new VideoGame("Cuphead","Cuphead is a classic run and gun action game heavily focused on boss battles.");
        VideoGame divinity= new VideoGame("Divinity: Original Sin II","A direct sequel to Divinity: Original Sin and another installment in " +
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

        //When
        gamesRepository.save(kcd);
        long kcd_Id= kcd.getId();
        gamesRepository.save(wow);
        long wow_Id= wow.getId();
        gamesRepository.save(cup);
        long cup_Id= cup.getId();
        gamesRepository.save(divinity);
        long divinity_Id= divinity.getId();

        //Then
        assertNotEquals(0,kcd_Id);
        assertNotEquals(0,wow_Id);
        assertNotEquals(0,cup_Id);
        assertNotEquals(0,divinity_Id);

    }
}
