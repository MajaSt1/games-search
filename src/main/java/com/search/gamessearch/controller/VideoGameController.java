package com.search.gamessearch.controller;

import com.search.gamessearch.model.Genre;
import com.search.gamessearch.model.VideoGame;
import com.search.gamessearch.repository.GenreRepository;
import com.search.gamessearch.service.VideoGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
/**
 * MODEL- informacja zwrotna (model i logiczna nazwa widoku)
 * */

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/videoGame")
public class VideoGameController {

    @Autowired
    GenreRepository genreRepository;
    @Autowired
    VideoGamesService videoGamesService;
/** MVC
 * */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/games")
    public String index(Model model) {
        List<VideoGame> vgames = videoGamesService.findAll();
        model.addAttribute("videoGames", vgames);
        return "videoGames";
    }
    @RequestMapping(value = "/newGame")
    public String addGame(Model model){
        model.addAttribute("addVideoGame", new VideoGame());
        return "addGame";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editGame(@PathVariable("id") Long videoGameId, Model model){
        model.addAttribute("game", videoGamesService.findOne(videoGameId));
        return "editGame";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(VideoGame videoGame){
        videoGamesService.save(videoGame);
        return "redirect:/videoGames";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") Long videoGameId) {
        videoGamesService.delete(videoGameId);
        return "redirect:/videoGames";
    }

    @RequestMapping(value = "addGameGenre/{id}", method = RequestMethod.GET)
    public String addGenre(@PathVariable("id") Long videoGameId, Model model){

        model.addAttribute("genre", genreRepository.findAll());
        model.addAttribute("game", videoGamesService.findOne(videoGameId).get());
        return "addGameGenre";
    }

    @RequestMapping(value="/games/{id}/genres", method=RequestMethod.GET)
    public String gamesAddGenre(@RequestParam(value="action", required=true) String action, @PathVariable Long id, @RequestParam Long genreId, Model model) {
        Optional<Genre> genre = genreRepository.findById(genreId);
        Optional<VideoGame> game = videoGamesService.findOne(id);

        if (game.isPresent() && action.equalsIgnoreCase("save")) {
            if (!game.get().hasGenre(genre.get())) {
                game.get().getGenres().add(genre.get());
            }
            videoGamesService.save(game.get());
            model.addAttribute("game", genreRepository.findById(id));
            model.addAttribute("genre", genreRepository.findAll());
            return "redirect:/videoGames";
        }

        model.addAttribute("VideoGames", videoGamesService.findAll());
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
