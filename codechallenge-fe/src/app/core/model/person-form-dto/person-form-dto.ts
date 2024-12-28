import { FormControl } from "@angular/forms";

export interface PersonFormDto {
    firstname: FormControl<string>;
    lastname: FormControl<string>;
    colour: FormControl<string>;
    zipcode: FormControl<string>;
    city: FormControl<string>;
}
