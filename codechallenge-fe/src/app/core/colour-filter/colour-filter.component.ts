import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { ColourDropdownDto } from '../model/person-form-dto/colour-dropdown-dto';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-colour-filter',
  imports: [ReactiveFormsModule],
  templateUrl: './colour-filter.component.html',
  styleUrl: './colour-filter.component.scss'
})
export class ColourFilterComponent implements OnInit {
  @Input()
  colours: string[] = [];

  @Output()
  filterSubmit: EventEmitter<string> = new EventEmitter<string>();

  coloursDropdown: ColourDropdownDto[] = [];

  colourControl = new FormControl<string>('');

  public ngOnInit(): void {
    this.coloursDropdown.push(this.generateColourDropdownDto("-- No colour --", ''));

    this.coloursDropdown.push(...this.colours.map((colour: string) => this.generateColourDropdownDto(colour, colour)).sort((a, b) => {
      return a.label?.localeCompare(b.label);
    }));
  }

  private generateColourDropdownDto(colourLabel: string, colourValue: string): ColourDropdownDto {
    return {
      label: colourLabel,
      value: colourValue
    };
  }

  protected filterList() {
    const colourFilter = this.colourControl.getRawValue();
    this.filterSubmit.emit(colourFilter)
  }
}