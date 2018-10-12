package com.search.gamessearch.controller;

import com.search.gamessearch.model.Genre;
import com.search.gamessearch.model.VideoGame;
import com.search.gamessearch.repository.GenreRepository;
import com.search.gamessearch.service.VideoGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/videoGame")
public class GenreController {

    @Autowired
    GenreRepository genreRepository;
    @Autowired
    VideoGamesService videoGamesService;


    @RequestMapping(value = "addGenre/{id}", method = RequestMethod.GET)
    public String addGenre(@PathVariable("id") Long videoGameId, Model model){

        model.addAttribute("genre", genreRepository.findAll());
        model.addAttribute("game", videoGamesService.findOne(videoGameId).get());
        return "addGenre";
    }

    @RequestMapping(value="/game/{id}/genre", method=RequestMethod.GET)
    public String GameDoAddGenre( @PathVariable Long id, @RequestParam Long genreId) {
        Optional<Genre> genre = genreRepository.findById(genreId);
        Optional<VideoGame> game = videoGamesService.findOne(id);

        if (game.isPresent())
            if (!game.get().hasGenre(genre.get())) {
                game.get().getGenres().add(genre.get());

                videoGamesService.addGame(game.get());
            }
        return "redirect:/videoGames";
    }
}
