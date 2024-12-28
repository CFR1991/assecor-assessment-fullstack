import { ComponentFixture, fakeAsync, TestBed } from '@angular/core/testing';

import { PersonDetailsComponent } from './person-details.component';
import { PersonFacade } from '../../shared/person-facade/person.facade';
import { createPersonFacadeMock } from '../../../../mocks/persons/person.facade.mock';
import { ActivatedRoute } from '@angular/router';



describe('PersonDetailsComponent', () => {
  const id = '123';

  let component: PersonDetailsComponent;
  let fixture: ComponentFixture<PersonDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonDetailsComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              params: {
                id: id
              },
            }
          }
        },
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(PersonDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
