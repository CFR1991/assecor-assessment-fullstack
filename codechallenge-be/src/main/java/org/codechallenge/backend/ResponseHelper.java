package org.codechallenge.backend;

import java.util.List;
import java.util.function.Supplier;

import org.codechallenge.backend.model.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ResponseHelper<T> {

    public ResponseEntity<PersonDto> toResponse(Supplier<PersonDto> supplier) {
        try {
            return ResponseEntity.ok(supplier.get());
        } catch (IllegalArgumentException e) {
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error("", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<List<T>> toResponseForList(Supplier<List<T>> supplier) {
        try {
            return ResponseEntity.ok(supplier.get());
        } catch (IllegalArgumentException e) {
            log.error("", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error("", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
