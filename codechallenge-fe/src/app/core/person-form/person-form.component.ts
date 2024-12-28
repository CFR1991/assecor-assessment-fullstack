import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { PersonDto } from '../../../../generated_sources';
import { Zipcodevalidator } from '../../shared/zipcode/zipcode.validator';
import { PersonFormDto } from '../model/person-form-dto/person-form-dto';
import { ColourDropdownDto } from '../model/person-form-dto/colour-dropdown-dto';
import { NavigationService } from '../../shared/navigation/navigation.service';

// Custom validator for zipcode
function zipcodeValidator(control: FormControl) {
  const value: string = control.value;
  // Check if it's a number with exactly 5 digits
  if (!value || !/^\d{5}$/.test(value)) {
    return { invalidZipcode: true }; // Return custom error if invalid
  }
  return null; // Return null if valid
}

@Component({
  selector: 'app-person-form',
  imports: [CommonModule, HttpClientModule, ReactiveFormsModule],
  templateUrl: './person-form.component.html',
  styleUrl: './person-form.component.scss'
})
export class PersonFormComponent implements OnInit {
  @Input()
  disabled: boolean = false;

  @Input()
  colours: string[] = [];

  @Output()
  formSubmitted: EventEmitter<any> = new EventEmitter();

  @Output()
  formCanceled: EventEmitter<any> = new EventEmitter();

  @Input()
  person!: PersonDto;

  fb = inject(NonNullableFormBuilder);

  backButtonTitle: string = '';

  coloursDropdown: ColourDropdownDto[] = [];
  public personForm: FormGroup;

  navigation = inject(NavigationService);

  public ngOnInit(): void {
    if (this.disabled) {
      this.backButtonTitle = 'Go back to Person Overview Page';
      this.colours = [this.person?.colour ?? ''];
    }
    else {
      this.backButtonTitle = 'Cancel';
    }
    this.coloursDropdown = this.colours.map((colour: string) => {
      return {
        label: colour,
        value: colour
      }
    }).sort((a, b) => {
      return a.label?.localeCompare(b.label);
    });

    this.personForm = new FormGroup<PersonFormDto>({
      firstname: this.fb.control({ value: this.person?.firstname ?? '', disabled: this.disabled }, [Validators.required, Validators.maxLength(50)],),
      lastname: this.fb.control({ value: this.person?.lastname ?? '', disabled: this.disabled }, [Validators.required, Validators.maxLength(50)]),
      colour: this.fb.control({ value: this.person?.colour ?? '', disabled: this.disabled }, [Validators.required]),
      zipcode: this.fb.control({ value: this.person?.zipcode?.toString() ?? '', disabled: this.disabled }, [Validators.required, Zipcodevalidator.valid()]),
      city: this.fb.control({ value: this.person?.city ?? '', disabled: this.disabled }, [Validators.required, Validators.maxLength(50)])
    })

  }


  onSubmit() {
    const personFormDto = this.personForm.getRawValue();
    console.log(personFormDto);
    const personCreateDto: PersonDto = {
      firstname: personFormDto.firstname,
      lastname: personFormDto.lastname,
      colour: personFormDto.colour,
      zipcode: personFormDto.zipcode,
      city: personFormDto.city,
    }

    this.formSubmitted.emit(personCreateDto);
  }


}