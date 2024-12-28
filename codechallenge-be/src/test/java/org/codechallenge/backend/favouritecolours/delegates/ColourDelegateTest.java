package org.codechallenge.backend.favouritecolours.delegates;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ColourDelegateTest {

    @InjectMocks
    private ColourDelegate colourDelegate;

    @Nested
    class GetPersonByIdTest {
        @Test
        public void testGetPersonById() {
            ResponseEntity<List<String>> response = colourDelegate.getColours();
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(List.of("blau", "grün", "violett", "rot", "gelb", "türkis", "weiß"), response.getBody());
        }
    }
}