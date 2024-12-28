import { TestBed } from '@angular/core/testing';

import { Zipcodevalidator } from './zipcode.validator';

describe('Zipcodevalidator', () => {
  let service: Zipcodevalidator;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Zipcodevalidator);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
