import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { InfoService } from '../../shared/info/info.service';

@Component({
  selector: 'app-info-modal',
  imports: [CommonModule, HttpClientModule],
  templateUrl: './info-modal.component.html',
  styleUrl: './info-modal.component.scss'
})
export class InfoModalComponent {
  private infoService = inject(InfoService);
  infoMessage$ = this.infoService.infoMessage$;
  showInfoModal$ = this.infoService.hasInfo$;

  close(){
    this.infoService.hasInfo$.next(false)
  }
}
