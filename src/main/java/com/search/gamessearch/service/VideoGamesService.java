package com.search.gamessearch.service;

import com.search.gamessearch.model.Genre;
import com.search.gamessearch.model.VideoGame;
import com.search.gamessearch.repository.GenreRepository;
import com.search.gamessearch.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VideoGamesService {
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    VideoGameRepository videoGameRepository;

    public List<VideoGame> findAll(){
        return videoGameRepository.findAll();
    }
    public VideoGame save(final VideoGame videoGame){
        return videoGameRepository.save(videoGame);
    }
    public void delete(long gameId){
        videoGameRepository.deleteById(gameId);
    }
    public Optional<VideoGame> findOne(long videoGame){
        return videoGameRepository.findById(videoGame);
    }

    public Map<Genre, List<VideoGame>> findAllGamesWithGenre(Long id){
        Map<Genre, List<VideoGame>> mgames= new HashMap<>();
        List<VideoGame> videoGames= videoGameRepository.findAll();
        videoGames.stream()
                .filter(v->v.getGenres().equals(genreRepository.findOne(id)))
                .collect(Collectors.toList());

        mgames.put(genreRepository.findOne(id),videoGames);

        return mgames;
    }
}
