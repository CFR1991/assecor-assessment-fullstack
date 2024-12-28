package org.codechallenge.backend.favouritecolours.delegates;

import java.util.List;

import org.codechallenge.backend.ResponseHelper;
import org.codechallenge.backend.api.ColoursApiDelegate;
import org.codechallenge.backend.favouritecolours.model.Colour;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColourDelegate extends ResponseHelper<String> implements ColoursApiDelegate {

    @Override
    public ResponseEntity<List<String>> getColours() {
        return toResponseForList(() -> List.of(Colour.values()).stream().map(Colour::getName).toList());
    }
}