import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RemoteService } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth.service';
import { SessionService } from '../session.service';

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
  sessionService: SessionService

  constructor(router: Router, remoteService: RemoteService, sessionService: SessionService,
    private cookieService: CookieService, 
    private authService: AuthService) {
    this.router = router
    this.username = ""
    this.password = ""
    this.remoteService = remoteService
    this.sessionService = sessionService;
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
        // fetch the data and store in the current session
        this.sessionService.updateSession(
          this.remoteService.decodeToken(this.cookieService.get("token")));
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
