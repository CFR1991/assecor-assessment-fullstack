import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class Zipcodevalidator {

  static valid(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (value && !/^\d{5}$/.test(value)) {
        return { invalid: true};
      }
      return null;
    };
  }
}
