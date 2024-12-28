package org.codechallenge.backend.favouritecolours.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codechallenge.backend.ResponseHelper;
import org.codechallenge.backend.api.PersonFileApiDelegate;
import org.codechallenge.backend.favouritecolours.logic.PersonAccessor;
import org.codechallenge.backend.favouritecolours.model.Colour;
import org.codechallenge.backend.model.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonFileDelegate extends ResponseHelper<PersonDto> implements PersonFileApiDelegate {

    private static final int ARRAY_SIZE = 4;
    private static final String SPLIT = ",";
    private final PersonAccessor personDelegate;

    @Override
    public ResponseEntity<List<PersonDto>> addPersonsFromFile(MultipartFile file) {
        return toResponseForList(() -> createFromFile(file));
    }

    public List<PersonDto> createFromFile(final MultipartFile file) {
        List<PersonDto> personList = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String remainingContent = "";
            String line;
            while ((line = reader.readLine()) != null) {
                final String lineToWorkWith = getLineToWorkWith(remainingContent, line);
                List<String> split = Arrays.asList(lineToWorkWith.split(SPLIT));
                if (!canCreatePerson(split)) {
                    remainingContent = lineToWorkWith;
                    continue;
                }
                addPersonToList(personList, split);
                remainingContent = "";
            }
            if (remainingContent != "") {
                throw new IllegalArgumentException("File to upload is incomplete");
            }
            return createPersons(personList);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getLineToWorkWith(String remainingContent, String line) {
        return remainingContent + line.strip();
    }

    private boolean canCreatePerson(List<String> split) {
        return split.size() >= ARRAY_SIZE;
    }

    private void addPersonToList(List<PersonDto> personCreateList, List<String> split) throws Exception {

        List<String> cityArray = getCityArray(getStrippedString(split, 2));
        PersonDto personCreateDto = new PersonDto() //
                .lastname(getStrippedString(split, 0)) //
                .firstname(getStrippedString(split, 1)) //
                .zipcode(getStrippedString(cityArray, 0)) //
                .city(getStrippedString(cityArray, 1))//
                .colour(Colour.fromId(getStrippedString(split, 3)).getName());
        personCreateList.add(personCreateDto);
    }

    private String getStrippedString(List<String> split, int i) {
        return split.get(i).strip();
    }

    private List<String> getCityArray(String string) throws Exception {
        string = string.replaceFirst(" ", SPLIT);
        String[] split = string.split(SPLIT);
        if (split.length != 2) {
            throw new IllegalArgumentException("Invalid city format");
        }
        split[1] = getClearedcity(split[1]);
        return Arrays.asList(split);
    }

    private String getClearedcity(String cityname) {
        String allowedChars = "[^A-Za-zÄÖÜäöüß -]";
        cityname = cityname.replaceAll(allowedChars, "");
        cityname=cityname.strip();
        if (cityname.endsWith("-")) {
            cityname = cityname.substring(0, cityname.length() - 1);
        }
        return cityname.strip();
    }

    private List<PersonDto> createPersons(List<PersonDto> personCreateDtos) {
        return personCreateDtos.stream().map(personDelegate::create).toList();
    }
}