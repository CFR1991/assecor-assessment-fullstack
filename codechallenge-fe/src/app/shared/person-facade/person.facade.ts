import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { PersonDto, PersonService } from '../../../../generated_sources';

@Injectable({
  providedIn: 'root'
})
export class PersonFacade {


  private personSevice = inject(PersonService);

  persons$ = new BehaviorSubject<PersonDto[] | undefined>(undefined);
  person$ = new BehaviorSubject<PersonDto | undefined>(undefined);
  colours$ = new BehaviorSubject<string[] | undefined>(undefined);


  public reset() {
    this.persons$.next(undefined);
    this.person$.next(undefined);
    this.colours$.next(undefined);
  }

  public setPersons(colour?: string | undefined): Observable<PersonDto[]> {
    return this.getPersons(colour).pipe(
      tap((persons: PersonDto[]) => {
        console.log("this.persons$.next(persons)", colour)
        this.persons$.next(persons);
      }),
    );
  }

  private getPersons(colour?: string | undefined) {
    return this.noFilter(colour) ? this.personSevice.getPersons() : this.personSevice.getPersonsByColour(colour);
  }

  private noFilter(colour: string) {
    return colour == undefined || colour === '';
  }

  public setPerson(id: number): Observable<PersonDto> {
    return this.personSevice.getPersonById(id).pipe(
      tap((person: PersonDto) => {
        this.person$.next(person);
      }),
    );
  }


  public addPerson(personCreateDto: PersonDto): Observable<PersonDto> {
    return this.personSevice.addPerson(personCreateDto);
  }

  public setColours(): Observable<string[]> {
    return this.personSevice.getColours().pipe(
      tap((colours: string[]) => {
        this.colours$.next(colours);
      }),
    );
  }
}