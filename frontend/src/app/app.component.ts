import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';

  constructor(private router: Router) {}

  getUserName() {
    const user = JSON.parse(localStorage.getItem('userInfo'));
    return user.username;
  }

  signOut() {
    localStorage.removeItem('userInfo');
    this.router.navigate(['sign-in']);
  }

  isSignedIn(): boolean {
    return !!localStorage.getItem('userInfo');
  }
}
