package com.search.gamessearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoGameDto {
    private long id;
    private String name;
    private String description;
    private List<Genre> genres;
}
