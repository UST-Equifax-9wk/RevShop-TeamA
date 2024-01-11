import { Component } from '@angular/core';
import { RemoteService, CartItemDto } from '../remote.service';
import { CookieService } from 'ngx-cookie-service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SessionService } from '../session.service';
import { Router } from '@angular/router';

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

  constructor(remoteService: RemoteService, 
    private router: Router,
    private sessionService: SessionService, 
    private cookieService: CookieService) {
    this.remoteService = remoteService;

    this.cart = [];
    const buyerId = sessionService.specificId;
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
        router.navigate(["/"]);
      }
    });
  }
  
}

