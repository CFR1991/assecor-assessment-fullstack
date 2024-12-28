package org.codechallenge.backend.favouritecolours.logic;

import java.util.List;

import org.codechallenge.backend.model.PersonDto;

public interface PersonAccessor {

    /**
     * Add a person.
     * 
     * @param personDto
     * @return created person
     */
    public PersonDto create(final PersonDto personDto);

    /*
     * Get persons.
     *
     * @return list of all persons
     */
    public List<PersonDto> findAll();

    /**
     * Get person for the id.
     *
     * @return persons with the id
     */

    public PersonDto findById(Long id);

    /**
     * Get persons with the given colour.
     *
     * @param colour the colour
     * @return list of all persons with the given colour
     */

    public List<PersonDto> findAllByColour(String colour);
}
