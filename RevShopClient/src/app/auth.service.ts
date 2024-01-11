import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject } from 'rxjs';
import { RemoteService } from './remote.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(private router: Router, private cookieService: CookieService) {
    // this.checkForLogoutOnStart();
  }
  

  get isLoggedIn() {
    return this.loggedIn.asObservable(); // Observable which is observed by components
  }

  login() {
    this.loggedIn.next(true);
    localStorage.setItem('shouldLogoutOnStart', 'false');
  }

  logout() {
    this.loggedIn.next(false);
    localStorage.removeItem('currentUser');
    this.cookieService.deleteAll();
    this.router.navigate(['/']);
  }

  checkForLogoutOnStart() {
    const shouldLogout = localStorage.getItem('shouldLogoutOnStart');
    if (shouldLogout === 'true') {
      this.logout();
    }
  }

  setLogoutOnTabClose() {
    localStorage.setItem('shouldLogoutOnStart', 'true');
  }

}
