import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RemoteService } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  router: Router
  username: string
  password: string
  remoteService: RemoteService

  constructor(router: Router, remoteService: RemoteService, private cookieService: CookieService, private authService: AuthService) {
    this.router = router
    this.username = ""
    this.password = ""
    this.remoteService = remoteService
    this.authService = authService;
  }
  
  submitLogin() {
    this.remoteService.login({username: this.username, password: this.password})
    .subscribe({
      next: (data) => {
        alert("Login Successful!");
        let loginData = data.body as LoginResponse;
        let token = loginData?.token;
        this.cookieService.set('token', token);
        console.log(data);
        this.authService.login();
        this.router.navigate(["/dashboard"]);
      },
      error: (error: HttpErrorResponse) => {
        alert("Access Denied! Bad credentials")
        console.log(error.error)
      }
    })
  }
}


interface LoginResponse {
  token: string;
}
