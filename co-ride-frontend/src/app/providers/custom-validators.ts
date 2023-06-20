import {
    AbstractControl,
    ValidatorFn,
    FormGroup,
    Validators
} from '@angular/forms';

export class CustomValidators {
    constructor() { }

    static mustMatch(controlName: string, matchingControlName: string) {
        return (formGroup: FormGroup) => {
            const control = formGroup.controls[controlName];
            const matchingControl = formGroup.controls[matchingControlName];

            if (matchingControl.errors && !matchingControl.errors['mustMatch']) {
                return;
            }

            // set error on matchingControl if validation fails
            if (control.value !== matchingControl.value) {
                matchingControl.setErrors({ mustMatch: true });
            } else {
                matchingControl.setErrors(null);
            }
            return null;
        };
    }

    static vehicleRequiredValidator(formControl: AbstractControl) {
        if (!formControl.parent) {
            return null;
        }

        if (formControl.parent.get('role')!.value == 'driver') {
            return Validators.required(formControl);
        }

        return null;
    }
}