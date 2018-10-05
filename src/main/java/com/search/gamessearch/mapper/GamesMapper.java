package com.search.gamessearch.mapper;

import com.search.gamessearch.model.VideoGame;
import com.search.gamessearch.model.VideoGameDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GamesMapper {

    public VideoGameDto maptoVideoGameDto(final VideoGame videoGame){
        return new VideoGameDto(videoGame.getId(),
                videoGame.getName(),
                videoGame.getDescription(),
                videoGame.getGenres());
    }

    public List<VideoGameDto> mapToListDto(final List<VideoGame> videoGames){
        return videoGames.stream()
                .map(t->new VideoGameDto(t.getId(),t.getName(),t.getDescription(),t.getGenres()))
                .collect(Collectors.toList());
    }

    public VideoGame mapToVideoGame(final VideoGameDto videoGameDto){
        return new VideoGame(videoGameDto.getId(),
                videoGameDto.getName(),
                videoGameDto.getDescription(),
                videoGameDto.getGenres());
    }

}
