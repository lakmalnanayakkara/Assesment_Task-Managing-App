import { Component, ViewEncapsulation } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { UserSignUpDetails } from '../shared/interface/user.interface';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-sign-up',
  standalone: false,
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css',
  encapsulation: ViewEncapsulation.None,
})
export class SignUpComponent {
  isLoading: boolean = false;
  isError: boolean = false;
  message: string;

  signUpForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    role: new FormControl('', [Validators.required]),
  });

  roles: { value: string; viewValue: string }[] = [
    { value: 'USER', viewValue: 'USER' },
  ];

  public subscriptions: Subscription = new Subscription();

  constructor(private userService: UserService, private router: Router) {}

  onSubmit() {
    this.isLoading = true;
    const userSignInDetails: UserSignUpDetails = {
      username: this.signUpForm.controls.username.value,
      password: this.signUpForm.controls.password.value,
      role: this.signUpForm.controls.role.value,
    };

    const signInUser = this.userService.userSignIn(userSignInDetails).subscribe(
      (data) => {
        localStorage.setItem('userInfo', JSON.stringify(data.data));
        this.isLoading = false;
        this.router.navigate(['']);
      },
      (error) => {
        this.isLoading = false;
        this.isError = true;
        this.message = error.error.data;
      }
    );

    this.subscriptions.add(signInUser);
  }

  changeIsError(newIsError: boolean) {
    this.isError = newIsError;
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
