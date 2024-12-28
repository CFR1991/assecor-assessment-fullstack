package org.codechallenge.backend.favouritecolours.db.logic;

import java.util.List;

import org.codechallenge.backend.favouritecolours.db.PersonMapper;
import org.codechallenge.backend.favouritecolours.db.PersonRepository;
import org.codechallenge.backend.favouritecolours.logic.PersonAccessor;
import org.codechallenge.backend.favouritecolours.model.Colour;
import org.codechallenge.backend.model.PersonDto;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonAccessorImpl implements PersonAccessor{
    public final PersonRepository personRepository;
    public final PersonMapper personMapper;


    /**
     * Add a person.
     * 
     * @param personDto
     * @return created person
     */
    @Override
    @Transactional
    public PersonDto create(final PersonDto personDto) {
        validatePerson(personDto);
        return personMapper.toDto(personRepository.save(personMapper.fromDto(personDto)));
    }

    private void validatePerson(final PersonDto personDto) {
        if (personDto == null) {
            throw new IllegalArgumentException("Person to add is null.");
        }
        if (personDto.getZipcode() == null || !personDto.getZipcode().matches("^\\d{5}$")) {
            throw new IllegalArgumentException("Person to add has ivalid zipcode.");
        }
    }

    /**
     * Get persons.
     *
     * @return list of all persons
     */
    @Override
    public List<PersonDto> findAll() {
        return personMapper.toDtos(personRepository.findAll());
    }

    /**
     * Get person for the id.
     *
     * @return persons with the id
     */
    @Override
    public PersonDto findById(Long id) {
        return personMapper.toDto(personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person with id" + id + "not found")));
    }

    /**
     * Get persons with the given colour.
     *
     * @param colour the colour
     * @return list of all persons with the given colour
     */
    @Override
    public List<PersonDto> findAllByColour(String colour) {
        return personMapper.toDtos(personRepository.findAllByColour(Colour.fromName(colour.toLowerCase())));
    }

}