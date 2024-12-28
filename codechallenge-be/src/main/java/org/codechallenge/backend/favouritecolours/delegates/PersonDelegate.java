package org.codechallenge.backend.favouritecolours.delegates;

import java.util.List;

import org.codechallenge.backend.ResponseHelper;
import org.codechallenge.backend.api.PersonsApiDelegate;
import org.codechallenge.backend.favouritecolours.logic.PersonAccessor;
import org.codechallenge.backend.model.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonDelegate extends ResponseHelper<PersonDto> implements PersonsApiDelegate {
    private final PersonAccessor personAccessor;

    @Override
    public ResponseEntity<List<PersonDto>> getPersons() {
        return toResponseForList(() -> personAccessor.findAll());
    }

    @Override
    public ResponseEntity<PersonDto> getPersonById(@NotNull Long id) {
        return toResponse(() -> personAccessor.findById(id));
    }

    @Override
    public ResponseEntity<List<PersonDto>> getPersonsByColour(@NotNull String colour) {
        return toResponseForList(() -> personAccessor.findAllByColour(colour));
    }

    @Override
    public ResponseEntity<PersonDto> addPerson(@Valid @NotNull PersonDto personCreateDto) {
        return toResponse(() -> personAccessor.create(personCreateDto));
    }
}