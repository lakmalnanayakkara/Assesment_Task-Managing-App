import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';

  getUserName() {
    const user = JSON.parse(localStorage.getItem('userInfo'));
    return user.username;
  }
}
