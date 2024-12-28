import { Component, inject } from '@angular/core';
import { ErrorService } from '../../shared/error/error.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-error-modal',
  imports: [CommonModule, HttpClientModule],
  templateUrl: './error-modal.component.html',
  styleUrl: './error-modal.component.scss'
})
export class ErrorModalComponent {
  private errorService = inject(ErrorService);
  errorMessage$ = this.errorService.errorMessage$;
  showErrorModal$ = this.errorService.hasError$;

  close(){
    this.errorService.hasError$.next(false)
  }
}
