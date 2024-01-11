import { Component } from '@angular/core';
import { RemoteService } from '../remote.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  // only want to load cart from repository when page is loaded for the session, otherwise use sessionStorage
  remoteService: RemoteService;
  username: string;

  constructor(remoteService: RemoteService, private cookieService: CookieService) {
    this.remoteService = remoteService;

    // fetch the token stored as a cookie from LoginComponent
    this.username = this.remoteService.decodeToken(this.cookieService.get('token'));
    console.log(this.username);
  }
  
}

