package com.search.gamessearch.service;

import com.search.gamessearch.model.Genre;
import com.search.gamessearch.model.VideoGame;
import com.search.gamessearch.repository.GenreRepository;
import com.search.gamessearch.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Transactional //connection open so that the Stream can actually be consumed
    public String findAllGamesWithGenre(String genre){
        List<String> videoGames= Collections.emptyList();

        try(Stream<Genre> stream= genreRepository.findByName(genre)){
            videoGames=stream.map(Genre::toString).collect(Collectors.toList());
        }
        System.out.println(videoGames);
        return videoGames.toString();
    }
}
