package com.search.gamessearch.repository;

import com.search.gamessearch.model.VideoGame;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface VideoGameRepository extends CrudRepository <VideoGame, Long> {
    @Override
    List<VideoGame> findAll();

    Optional<VideoGame> findById(long id);

    @Modifying
    void deleteById(long id);
}
