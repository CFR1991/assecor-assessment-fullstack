package org.codechallenge.backend.favouritecolours.db;

import java.util.List;

import org.codechallenge.backend.favouritecolours.model.Colour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByColour(Colour colour);
}
