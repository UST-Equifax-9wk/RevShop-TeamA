import { Component } from '@angular/core';
import { RemoteService, CartItemDto } from '../remote.service';
import { CookieService } from 'ngx-cookie-service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  // only want to load cart from repository when page is loaded for the session, otherwise use sessionStorage
  remoteService: RemoteService;
  cart: CartItemDto[];

  constructor(remoteService: RemoteService, private cookieService: CookieService) {
    this.remoteService = remoteService;

    // fetch the token stored as a cookie from LoginComponent
    let tokenData = this.remoteService.decodeToken(this.cookieService.get('token'));
    const accountType = tokenData.accountType;

    this.cart = [];
    // constant value for now, want to be able to retrieve the actual buyerId somehow
    const buyerId = tokenData.specId;
    this.remoteService.getCart(buyerId)
    .subscribe({
      next: (data) => {
        this.cart = data.body as CartItemDto[];
        this.cart.sort((ci1, ci2) => ci1.cartItemId - ci2.cartItemId);
        // console.log(this.cart);
      },
      error: (error: HttpErrorResponse) => {
        alert("Cart could not be retrieved!");
        console.log(error.error);
      }
    });
  }
  
}

