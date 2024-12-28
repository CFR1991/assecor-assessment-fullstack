import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ErrorService {
  public errorMessage$ = new BehaviorSubject<string>('');
  public hasError$ = new BehaviorSubject<boolean>(false);


  showError(message: string): void {
    this.errorMessage$.next(message);
    this.hasError$.next(true);
  }
}
