package org.codechallenge.backend.favouritecolours.delegates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.codechallenge.backend.favouritecolours.db.logic.PersonAccessorImpl;
import org.codechallenge.backend.favouritecolours.model.Colour;
import org.codechallenge.backend.model.PersonDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

@ExtendWith(MockitoExtension.class)
class PersonFileDelegateTest {

    @Mock
    private PersonAccessorImpl personAccessorImpl;

    @InjectMocks
    private PersonFileDelegate personFileDelegate;

    @Test
    public void whenAddPersonsFromFile() throws IOException {
        PersonDto person1 = new PersonDto();
        person1.setFirstname("Anders");
        person1.setLastname("Andersson");
        person1.setColour(Colour.GRUEN.getName());
        person1.setZipcode("32132");
        person1.setCity("Schweden");

        when(personAccessorImpl.create(person1)).thenReturn(person1);

        PersonDto person2 = new PersonDto();
        person2.setFirstname("Bertram");
        person2.setLastname("Bart");
        person2.setColour(Colour.BLAU.getName());
        person2.setZipcode("12313");
        person2.setCity("Was-Weißich");

        when(personAccessorImpl.create(person2)).thenReturn(person2);

        PersonDto person3 = new PersonDto();
        person3.setFirstname("Gerda");
        person3.setLastname("Gerber");
        person3.setColour(Colour.VIOLETT.getName());
        person3.setZipcode("76535");
        person3.setCity("Bad Woanders");

        when(personAccessorImpl.create(person3)).thenReturn(person3);

        MockMultipartFile multipartFile = getMultipartFile("sample-input-part.csv");

        ResponseEntity<List<PersonDto>> response = personFileDelegate
                .addPersonsFromFile(multipartFile);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(), List.of(person1, person2, person3));
    }

    @Test
    public void whenAddPersonsFromFileWithIncompleteFileThenReturnREWithBadRequest() throws IOException {
        MockMultipartFile multipartFile = getMultipartFile("sample-input-incomplete.csv");

        ResponseEntity<List<PersonDto>> response = personFileDelegate
                .addPersonsFromFile(multipartFile);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void whenAddPersonsFromFileWithFileWithInvalidCityThenReturnREWithBadRequest() throws IOException {
        MockMultipartFile multipartFile = getMultipartFile("sample-input-invalid-city.csv");

        ResponseEntity<List<PersonDto>> response = personFileDelegate
                .addPersonsFromFile(multipartFile);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void whenAddPersonsAndCreateThrowsIllegalRuntimeExceptionThenReturnREWithBadRequest() throws IOException {
        MockMultipartFile multipartFile = getMultipartFile("sample-input-part.csv");
        when(personAccessorImpl.create(any())).thenThrow(new RuntimeException());

        ResponseEntity<List<PersonDto>> response = personFileDelegate
                .addPersonsFromFile(multipartFile);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void whenAddPersonsAndCreateThrowsIllegalArgumentExceptionThenReturnREWithBadRequest() throws IOException {
        MockMultipartFile multipartFile = getMultipartFile("sample-input-part.csv");
        when(personAccessorImpl.create(any())).thenThrow(new IllegalArgumentException());

        ResponseEntity<List<PersonDto>> response = personFileDelegate
                .addPersonsFromFile(multipartFile);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    private MockMultipartFile getMultipartFile(final String filename) throws IOException {
        File fileResource = new File("src/test/resources/" + filename);
        byte[] fileBytes = Files.readAllBytes(fileResource.toPath());
        MockMultipartFile multipartFile = new MockMultipartFile(
                "attachments", filename,
                "application/octet-stream",
                fileBytes);
        return multipartFile;
    }
}