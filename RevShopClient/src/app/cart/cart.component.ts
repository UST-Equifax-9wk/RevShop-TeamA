import { Component } from '@angular/core';
import { RemoteService } from '../remote.service';

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

  constructor(remoteService: RemoteService) {
    this.remoteService = remoteService;
  }
  
  
}
