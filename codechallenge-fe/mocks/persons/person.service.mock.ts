import { of } from "rxjs";
import { colourListMock, personDtoListMock } from "./person-dto.mock";


export const createPersonServiceMock = function () {


    const personServiceMock = {
        getPersons: jest.fn(),

        getPersonById: jest.fn(),
        addPerson: jest.fn(),
        getColours: jest.fn()
    };

    personServiceMock.getPersons.mockReturnValue(of(personDtoListMock));
    personServiceMock.getPersonById.mockReturnValue(of(personDtoListMock[0]));
    personServiceMock.getColours.mockReturnValue(of(colourListMock[0]));

}

