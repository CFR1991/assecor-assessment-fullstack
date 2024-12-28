import { inject, Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ErrorService } from './error.service';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  private errorService = inject(ErrorService);

  intercept(req: HttpRequest<any>, next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        const errorMsg = `HTTP Error: ${error.status} - ${error.message}`;
        console.error(errorMsg);
        this.errorService.showError(errorMsg); //
        return of();
      })
    );
  }
}
