import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonOverviewComponent } from './person-overview.component';
import { createPersonFacadeMock } from '../../../../mocks/persons/person.facade.mock';
import { PersonFacade } from '../../shared/person-facade/person.facade';

describe('PersonOverviewComponent', () => {
  let component: PersonOverviewComponent;
  let fixture: ComponentFixture<PersonOverviewComponent>;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      imports: [PersonOverviewComponent],
    })
      .compileComponents();

    fixture = TestBed.createComponent(PersonOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
