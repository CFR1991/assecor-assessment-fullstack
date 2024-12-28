package org.codechallenge.backend.favouritecolours.delegates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.codechallenge.backend.favouritecolours.db.logic.PersonAccessorImpl;
import org.codechallenge.backend.model.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PersonDelegateTest {

    @Mock
    private PersonAccessorImpl personAccessor;

    @InjectMocks
    private PersonDelegate personDelegate;

    private PersonDto personDto;
    List<PersonDto> personDtoList = List.of();


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
    }

    @Nested
    class GetPersonsTest {
        @Test
        void whenGetPersons() {
            when(personAccessor.findAll()).thenReturn(personDtoList);

            ResponseEntity<List<PersonDto>> response = personDelegate.getPersons();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(personDtoList, response.getBody());
        }

        @Test
        public void whenGetPersonsAndFindAllThrowsRuntimeExceptionThenReturnREWithInternalServerError() {
            when(personAccessor.findAll()).thenThrow(new RuntimeException());

            ResponseEntity<List<PersonDto>> response = personDelegate.getPersons();

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertNull(
                    response.getBody());
        }

        @Test
        public void whenGetPersonsAndFindAllThrowsIllegalArgumentExceptionThenReturnREWithBadRequest() {
            when(personAccessor.findAll()).thenThrow(new IllegalArgumentException());

            ResponseEntity<List<PersonDto>> response = personDelegate.getPersons();

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertNull(
                    response.getBody());
        }
    }

    @Nested
    class GetPersonByIdTest {

        @Test
        void whenGetPersonById() {
            when(personAccessor.findById(1L)).thenReturn(personDto);

            ResponseEntity<PersonDto> response = personDelegate.getPersonById(1L);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(personDto, response.getBody());
        }

        @Test
        public void whenGetPersonByIdAndFindByIdThrowsRuntimeExceptionThenReturnREWithInternalServerError() {
            when(personAccessor.findById(1L)).thenThrow(new RuntimeException());

            ResponseEntity<PersonDto> response = personDelegate.getPersonById(1L);

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertNull(
                    response.getBody());
        }

        @Test
        public void whenGetPersonByIdAndFindByIdThrowsIllegalArgumentExceptionThenReturnREWithBadRequest() {
            when(personAccessor.findById(1L)).thenThrow(new IllegalArgumentException());

            ResponseEntity<PersonDto> response = personDelegate.getPersonById(1L);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertNull(
                    response.getBody());
        }
    }

    @Nested
    class GetPersonsByColourTest {

        @Test
        void whenGetPersonsByColour() {
            when(personAccessor.findAllByColour("gelb")).thenReturn(personDtoList);

            ResponseEntity<List<PersonDto>> response = personDelegate.getPersonsByColour("gelb");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(personDtoList, response.getBody());
        }

        @Test
        public void whenGetPersonsByColourAndFindAllByColourThrowsRuntimeExceptionThenReturnREWithInternalServerError() {
            when(personAccessor.findAllByColour("gelb")).thenThrow(new RuntimeException());

            ResponseEntity<List<PersonDto>> response = personDelegate.getPersonsByColour("gelb");

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertNull(
                    response.getBody());
        }

        @Test
        public void whenGetPersonsByColourAndFindAllByColourThrowsIllegalArgumentExceptionThenReturnREWithBadRequest() {
            when(personAccessor.findAllByColour("gelb")).thenThrow(new IllegalArgumentException());

            ResponseEntity<List<PersonDto>> response = personDelegate.getPersonsByColour("gelb");

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertNull(
                    response.getBody());
        }
    }

    @Nested
    class AddPersonTest {
        @Test
        void whenAddPersonByColour() {
            when(personAccessor.create(personDto)).thenReturn(personDto);

            ResponseEntity<PersonDto> response = personDelegate.addPerson(personDto);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(personDto, response.getBody());
        }

        @Test
        public void whenAddPersonAndCreateThrowsRuntimeExceptionThenReturnREWithInternalServerError() {
            when(personAccessor.create(personDto)).thenThrow(new RuntimeException());

            ResponseEntity<PersonDto> response = personDelegate.addPerson(personDto);

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertNull(
                    response.getBody());
        }

        @Test
        public void whenAddPersonAndCreateThrowsIllegalArgumentExceptionThenReturnREWithBadRequest() {
            when(personAccessor.create(personDto)).thenThrow(new IllegalArgumentException());

            ResponseEntity<PersonDto> response = personDelegate.addPerson(personDto);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertNull(
                    response.getBody());
        }
    }
}