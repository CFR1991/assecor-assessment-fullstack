package org.codechallenge.backend.favouritecolours.db;

import java.util.List;

import org.codechallenge.backend.favouritecolours.model.Colour;
import org.codechallenge.backend.model.PersonDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {

    private ModelMapper modelmapper = new ModelMapper();

    /**
     * @param persons list of internal classes
     * @return list of External DTOs
     */
    public List<PersonDto> toDtos(List<Person> persons) {
        return persons.stream().map(this::toDto).toList();
    }

    /**
     * @param person internal classes
     * @return external DTO
     */
    public PersonDto toDto(Person person) {
        PersonDto personDto = this.modelmapper.map(person, PersonDto.class);
        personDto.setColour(person.getColour().getName());
        return personDto;
    }

    /**
     * @param personDto external class
     * @return external DTO
     */
    public Person fromDto(PersonDto personDto) {
        Person person = this.modelmapper.map(personDto, Person.class);
        person.setColour(Colour.fromName(personDto.getColour().toLowerCase()));
        return person;
    }
}