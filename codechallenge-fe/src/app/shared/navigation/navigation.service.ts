import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {
  router = inject(Router);

  public goToPersonOverviewPage(): void {
    this.router.navigate(['']);
  }
  public goToPersonDetailPage(id: number): void {
    this.router.navigate(['personDetails', id]);
  }
  public goToCreatePersonPage(): void {
    this.router.navigate(['addPerson']);
  }
}
