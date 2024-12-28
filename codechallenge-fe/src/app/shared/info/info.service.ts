import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InfoService {

  public infoMessage$ = new BehaviorSubject<string>('');
  public hasInfo$ = new BehaviorSubject<boolean>(false);


  showInfo(message: string): void {
    this.infoMessage$.next(message);
    this.hasInfo$.next(true);
  }

}
