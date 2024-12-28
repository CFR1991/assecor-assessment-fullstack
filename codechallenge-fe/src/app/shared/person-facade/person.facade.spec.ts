import { TestBed } from '@angular/core/testing';

import { PersonService } from '../../../../generated_sources';
import { createPersonServiceMock } from '../../../../mocks/persons/person.service.mock';
import { PersonFacade } from './person.facade';

describe('PersonFacade', () => {

  let service: PersonFacade;
  let personServiceMock: any;
  beforeEach(() => {
    personServiceMock: createPersonServiceMock();

    TestBed.configureTestingModule({
      providers: [
        {
          provide: PersonService,
          useValue: personServiceMock
        }]
    });
    service = TestBed.inject(PersonFacade);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
