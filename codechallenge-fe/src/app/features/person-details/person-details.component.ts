import { Component, inject, OnInit } from '@angular/core';
import { PersonFormComponent } from '../../core/person-form/person-form.component';
import { ErrorService } from '../../shared/error/error.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonFacade } from '../../shared/person-facade/person.facade';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { catchError, of } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Title } from '@angular/platform-browser';

@UntilDestroy()
@Component({
  selector: 'app-person-details',
  imports: [CommonModule, HttpClientModule, PersonFormComponent],
  templateUrl: './person-details.component.html',
  styleUrl: './person-details.component.scss'
})
export class PersonDetailsComponent implements OnInit {

  private errorService = inject(ErrorService);
  private activatedRoute = inject(ActivatedRoute);
  public personFacade = inject(PersonFacade);
  public person$ = this.personFacade.person$;
  public id: number = this.activatedRoute.snapshot.params.id;
  public titleService = inject(Title);
  public title!: string;


  public ngOnInit(): void {
    this.title = this.titleService.getTitle();

    this.personFacade.setPerson(this.id).pipe(
      untilDestroyed(this),
      catchError((error) => {
        this.errorService.showError(`The person with the id ${this.id} could not be loaded.`);
        return of();
      }),
    ).subscribe();
  }


}