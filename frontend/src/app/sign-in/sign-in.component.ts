import { Component, ViewEncapsulation } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserService } from '../service/user.service';
import { UserSignInDetails } from '../shared/interface/user.interface';

@Component({
  selector: 'app-sign-in',
  standalone: false,
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css',
  encapsulation: ViewEncapsulation.None,
})
export class SignInComponent {
  isLoading: boolean = false;
  isError: boolean = false;
  message: string;

  signInForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  public subscriptions: Subscription = new Subscription();

  constructor(private userService: UserService, private router: Router) {}

  onSubmit() {
    this.isLoading = true;
    const userSignInDetails: UserSignInDetails = {
      username: this.signInForm.controls.username.value,
      password: this.signInForm.controls.password.value,
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
