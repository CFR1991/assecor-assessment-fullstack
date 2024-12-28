import { of } from "rxjs";
import { colourListMock, personDtoListMock } from "./person-dto.mock";


export const createPersonFacadeMock = function () {

    const personFacadeMock = {
        setPersons: jest.fn(),
        setPerson: jest.fn(),
        addPerson: jest.fn(),
        setColours: jest.fn()
    };

    personFacadeMock.setPersons.mockReturnValue(of(personDtoListMock));
    personFacadeMock.setPerson.mockReturnValue(of(personDtoListMock[0]));
    personFacadeMock.setColours.mockReturnValue(of(colourListMock[0]));
}
