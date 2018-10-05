package com.search.gamessearch.controller;

import com.search.gamessearch.model.Genre;
import com.search.gamessearch.model.VideoGame;
import com.search.gamessearch.repository.GenreRepository;
import com.search.gamessearch.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 * MODEL- informacja zwrotna (model i logiczna nazwa widoku)
 * */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/videoGame")
public class VideoGameController {

    @Autowired
    VideoGameRepository gameRepository;
    @Autowired
    GenreRepository genreRepository;
/** MVC
 * */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/games")
    public String index(Model model) {
        List<VideoGame> vgames = (List<VideoGame>) gameRepository.findAll();
        model.addAttribute("videoGames", vgames);
        return "videoGames";
    }
    @RequestMapping(value = "add")
    public String addGame(Model model){
        model.addAttribute("videoGames", new VideoGame());
        return "addGame";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editGame(@PathVariable("id") Long videoGameId, Model model){
        model.addAttribute("student", gameRepository.findById(videoGameId));
        return "editGame";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(VideoGame videoGame){
        gameRepository.save(videoGame);
        return "redirect:/videoGames";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") Long videoGameId, Model model) {
        gameRepository.deleteById(videoGameId);
        return "redirect:/videoGames";
    }

    @RequestMapping(value = "addGameGenre/{id}", method = RequestMethod.GET)
    public String addGenre(@PathVariable("id") Long videoGameId, Model model){

        model.addAttribute("genre", genreRepository.findAll());
        model.addAttribute("game", gameRepository.findById(videoGameId).get());
        return "addGameGenre";
    }

    @RequestMapping(value="/student/{id}/courses", method=RequestMethod.GET)
    public String gamesAddGenre(@RequestParam(value="action", required=true) String action, @PathVariable Long id, @RequestParam Long genreId, Model model) {
        Optional<Genre> genre = genreRepository.findById(genreId);
        Optional<VideoGame> game = gameRepository.findById(id);

        if (game.isPresent() && action.equalsIgnoreCase("save")) {
            if (!game.get().hasGenre(genre.get())) {
                game.get().getGenres().add(genre.get());
            }
            gameRepository.save(game.get());
            model.addAttribute("game", genreRepository.findById(id));
            model.addAttribute("genre", genreRepository.findAll());
            return "redirect:/games";
        }

        model.addAttribute("VideoGames", gameRepository.findAll());
        return "redirect:/games";

    }

}
