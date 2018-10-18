package com.search.gamessearch.controller;

import com.search.gamessearch.model.VideoGame;
import com.search.gamessearch.repository.GenreRepository;
import com.search.gamessearch.service.VideoGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller
public class VideoGameController {

    @Autowired
    GenreRepository genreRepository;
    @Autowired
    VideoGamesService videoGamesService;

    //   Show all games
    @RequestMapping(value = "/videoGames", method = RequestMethod.GET)
    public String showVGamesList (Map<String, Object> model) {
        List<VideoGame> vgames = videoGamesService.findAll();
        model.put("videoGames", vgames);
        return "videoGames";
    }

    @RequestMapping(value = "/newGame")
    public String addGame(Model model){
        model.addAttribute("videoGame", new VideoGame());
        return "addGame";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("videoGame") VideoGame videoGame, BindingResult result) {
        if (result.hasErrors()) {
            return "addGame";
        } else {
            videoGamesService.addGame(videoGame);
            return "redirect:/videoGames";
        }
    }
    @RequestMapping(value = "/edit/{id}")
    public String editGame(@PathVariable("id") Long videoGameId, Model model){
        model.addAttribute("game", videoGamesService.findOne(videoGameId));
        return "editGame";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") Long videoGameId) {
        videoGamesService.delete(videoGameId);
        return "redirect:/videoGames";
    }

    @RequestMapping(value = "/genres/games",method = RequestMethod.GET)
    public String findGamesWithGenre(@RequestParam String genre, Model model){
        model.addAttribute("games",videoGamesService.findAllGamesWithGenre(genre));
        return "gamesWithGenre";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/games")
    public @ResponseBody List<VideoGame> getGames() { //REST
        return videoGamesService.findAll();
    }

}
