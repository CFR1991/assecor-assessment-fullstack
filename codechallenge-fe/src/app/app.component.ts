import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { ErrorModalComponent } from './features/error-modal/error-modal.component';
import { InfoModalComponent } from "./features/info-modal/info-modal.component";
import { HttpErrorInterceptor } from './shared/error/error.interceptor';
import { PersonFacade } from './shared/person-facade/person.facade';
import { filter, tap } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ErrorModalComponent, ReactiveFormsModule,
    HttpClientModule, CommonModule, InfoModalComponent, ErrorModalComponent],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    }
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  router = inject(Router);
  personFacade = inject(PersonFacade);

  public ngOnInit() {
    this.router.events.pipe(
      filter((event) => event instanceof NavigationEnd),
      tap(() => {
        this.personFacade.reset();
      }
      )
    ).subscribe();
  }
}