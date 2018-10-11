package com.search.gamessearch.repository;

import com.search.gamessearch.model.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Transactional
public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAll();

    Optional<Genre> findById(Long id);

    @Query("Select c from Genre c where c.name = ?1")
    Stream<Genre> findByName(String name);
}
