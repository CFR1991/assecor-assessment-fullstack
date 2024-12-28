package org.codechallenge.backend.favouritecolours.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.codechallenge.backend.favouritecolours.db.logic.PersonAccessorImpl;
import org.codechallenge.backend.favouritecolours.model.Colour;
import org.codechallenge.backend.model.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonAccessorTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonAccessorImpl personAccessor;

    private PersonDto personDto;
    List<PersonDto> personDtoList = List.of();

    private Person person;
    List<Person> personList = List.of();

    @BeforeEach
    public void setUp() {
        personDto = new PersonDto();
        personDto.setId(1L);
        personDto.setFirstname("Johanna");
        personDto.setLastname("Meier");
        personDto.setColour("weiss");
        personDto.setZipcode("12345");
        personDto.setCity("London");
        personDtoList = List.of(personDto);

        person = new Person();
        person.setId(1L);
        person.setFirstname("Johanna");
        person.setLastname("Meier");
        person.setColour(Colour.WEISS);
        person.setZipcode("12345");
        person.setCity("London");
        personList = List.of(person);
    }

    @Nested
    class GetPersonsTest {
        @Test
        void whenGetPersons() {
            when(personRepository.findAll()).thenReturn(personList);
            when(personMapper.toDtos(personList)).thenReturn(personDtoList);

            List<PersonDto> result = personAccessor.findAll();

            assertEquals(personDtoList, result);
        }

        @Test
        public void whenGetPersonsThrowsRuntimeExceptionThenThrowRuntimeException() {
            when(personRepository.findAll()).thenThrow(new RuntimeException());

            assertThrows(RuntimeException.class, () -> personAccessor.findAll());
        }

        @Test
        public void whenGetPersonsThrowsIllegalArgumentExceptionThenThrowIllegalArgumentException() {
            when(personRepository.findAll()).thenThrow(new IllegalArgumentException());

            assertThrows(IllegalArgumentException.class, () -> personAccessor.findAll());
        }
    }

    @Nested
    class findByIdTest {
        @Test
        public void whenFindById() {
            when(personRepository.findById(1L)).thenReturn(Optional.of(person));
            when(personMapper.toDto(person)).thenReturn(personDto);

            PersonDto result = personAccessor.findById(1L);
            assertEquals(personDto, result);
        }

        @Test
        public void whenFindByWithIdNotFoundThenThrowRuntimeException() {
            when(personRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () -> personAccessor.findById(1L));
        }

        @Test
        public void whenFindByIdTRepositoryhrowsRuntimeExceptionThenThrowRuntimeException() {
            when(personRepository.findById(-1L)).thenThrow(new RuntimeException());
            assertThrows(RuntimeException.class, () -> personAccessor.findById(-1L));
        }

        @Test
        public void whenFindByIdMapperThrowsRuntimeExceptionThenThrowRuntimeException() {
            when(personRepository.findById(1L)).thenReturn(Optional.of(person));
            when(personMapper.toDto(person)).thenThrow(new RuntimeException());

            assertThrows(RuntimeException.class, () -> personAccessor.findById(-1L));
        }
    }

    @Nested
    class findAllByColourTest {
        @Test
        public void whenFindAllByColour() {
            when(personRepository.findAllByColour(Colour.fromName("gelb")))
                    .thenReturn(personList);
            when(personMapper.toDtos(personList)).thenReturn(personDtoList);

            List<PersonDto> result = personAccessor.findAllByColour("gelb");

            assertEquals(personDtoList, result);
        }

        @Test
        public void whenFindAllByColourWithInvaildColourThenThrowRuntimeException() {
            assertThrows(RuntimeException.class, () -> personAccessor.findAllByColour("lilablassblau"));
        }

        @Test
        public void whenFindAllByColourRepoThrowsRuntimeExceptionThenThrowRuntimeException() {
            when(personRepository.findAllByColour(Colour.fromName("gelb")))
                    .thenThrow(new RuntimeException());

            assertThrows(RuntimeException.class, () -> personAccessor.findAllByColour("gelb"));
        }

        @Test
        public void whenFindAllByColourMapperThrowsRuntimeExceptionThenThrowRuntimeException() {
            when(personRepository.findAllByColour(Colour.fromName("gelb")))
                    .thenReturn(personList);
            when(personMapper.toDtos(personList))
                    .thenThrow(new RuntimeException());

            assertThrows(RuntimeException.class, () -> personAccessor.findAllByColour("gelb"));
        }
    }

    @Nested
    class createTest {
        @Test
        public void whenCreate() {
            when(personMapper.fromDto(personDto)).thenReturn(person);
            when(personRepository.save(person)).thenReturn(person);
            when(personMapper.toDto(person)).thenReturn(personDto);

            PersonDto result = personAccessor.create(personDto);

            assertEquals(personDto, result);

        }

        @Test
        public void whenCreateNullAsPerson() {
            assertThrows(IllegalArgumentException.class, () -> personAccessor.create(null));
        }

        @Test
        public void whenCreateEmptyZipCode() {
            personDto.setZipcode(null);
            assertThrows(IllegalArgumentException.class, () -> personAccessor.create(personDto));
        }

        @Test
        public void whenCreateWwithInvalidZipCodeToShort() {
            personDto.setZipcode("1234");

            assertThrows(IllegalArgumentException.class, () -> personAccessor.create(personDto));
        }

        @Test
        public void whenCreateWithInvalidZipCodeNotOnlyDigitThenThrowIllegalArgumentException() {
            personDto.setZipcode("AB234");

            assertThrows(IllegalArgumentException.class, () -> personAccessor.create(personDto));
        }

        @Test
        public void whenCreateRepositoryThrowsRuntimeExceptionThenThrowRuntimeException() {
            when(personMapper.fromDto(personDto)).thenReturn(person);
            when(personRepository.save(person))
                    .thenThrow(new RuntimeException());

            assertThrows(RuntimeException.class, () -> personAccessor.create(personDto));
        }

        @Test
        public void whenCreateMapperFromDtoThrowsRuntimeExceptionThenThrowRuntimeException() {
            when(personMapper.fromDto(personDto))
                    .thenThrow(new RuntimeException());

            assertThrows(RuntimeException.class, () -> personAccessor.create(personDto));
        }

        @Test
        public void whenCreateMapperToDtoThrowsRuntimeExceptionThenThrowRuntimeException() {
            when(personMapper.fromDto(personDto)).thenReturn(person);
            when(personRepository.save(person)).thenReturn(person);
            when(personMapper.toDto(person))
                    .thenThrow(new RuntimeException());

            assertThrows(RuntimeException.class, () -> personAccessor.create(personDto));
        }
    }
}