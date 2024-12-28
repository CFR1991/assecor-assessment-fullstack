import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { catchError, of, tap } from 'rxjs';
import { PersonDto } from '../../../../generated_sources';
import { PersonFormComponent } from '../../core/person-form/person-form.component';
import { ErrorService } from '../../shared/error/error.service';
import { InfoService } from '../../shared/info/info.service';
import { NavigationService } from '../../shared/navigation/navigation.service';
import { PersonFacade } from '../../shared/person-facade/person.facade';

@UntilDestroy()
@Component({
  selector: 'app-person-overview',
  imports: [CommonModule, HttpClientModule, PersonFormComponent],
  templateUrl: './person-create.component.html',
  styleUrl: './person-create.component.scss'
})
export class PersonCreateComponent implements OnInit {
  infoService = inject(InfoService);

  errorService = inject(ErrorService);
  personFacade = inject(PersonFacade);

  colours$ = this.personFacade.colours$;
  navigation=inject(NavigationService);

  titleService = inject(Title);
  title!: string;


  public ngOnInit(): void {
    this.title = this.titleService.getTitle();
    this.personFacade.setColours().pipe(
      untilDestroyed(this),
      catchError((error) => {
        this.errorService.showError('The list of the colours could not be loaded.');
        return of();
      }),
    ).subscribe();
  }

  handleFormSubmit(personDto: PersonDto) {
    this.personFacade.addPerson(personDto).pipe(
      untilDestroyed(this),
      tap(() => {
        this.infoService.showInfo('The person was added successfully.');
        this.navigation.goToPersonOverviewPage()
      }
      ),
      catchError((error) => {
        this.errorService.showError('The person could not be added');
        return of();
      })
    ).subscribe();
  }
}