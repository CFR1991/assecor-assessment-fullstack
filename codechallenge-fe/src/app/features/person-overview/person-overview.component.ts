import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { catchError, of } from 'rxjs';
import { ColourFilterComponent } from "../../core/colour-filter/colour-filter.component";
import { PersonTableComponent } from '../../core/person-table/person-table.component';
import { ErrorService } from '../../shared/error/error.service';
import { PersonFacade } from '../../shared/person-facade/person.facade';
import { Title } from '@angular/platform-browser';
import { NavigationService } from '../../shared/navigation/navigation.service';

@UntilDestroy()
@Component({
  selector: 'app-person-overview',
  imports: [CommonModule, HttpClientModule, PersonTableComponent, ColourFilterComponent],
  templateUrl: './person-overview.component.html',
  styleUrl: './person-overview.component.scss'
})
export class PersonOverviewComponent implements OnInit {

  private errorService = inject(ErrorService);
  protected navigation = inject(NavigationService);
  protected personFacade = inject(PersonFacade);

  protected persons$ = this.personFacade.persons$;

  protected colours$ = this.personFacade.colours$;
  protected titleService = inject(Title);
  protected title!: string;


  public ngOnInit(): void {
    this.title = this.titleService.getTitle();

    this.setPersons();
    this.personFacade.setColours().pipe(
      untilDestroyed(this),
      catchError((error) => {
        this.errorService.showError('The list of the colours could not be loaded.');
        return of();
      }),
    ).subscribe();
  }

  private setPersons(colour?: string | undefined) {
    this.personFacade.setPersons(colour).pipe(
      untilDestroyed(this),
      catchError((error) => {
        this.errorService.showError('The list of the persons could not be loaded.');
        return of();
      })
    ).subscribe();
  }

  public reloadPersonList(filter: string | undefined): void {
    this.setPersons(filter);
  }
}