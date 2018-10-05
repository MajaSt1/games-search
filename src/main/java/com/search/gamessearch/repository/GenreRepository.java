package com.search.gamessearch.repository;

import com.search.gamessearch.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional <Genre> findById(Long id);
}
