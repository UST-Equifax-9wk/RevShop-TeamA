import { Component } from '@angular/core';
import { OrderDto, OrderItemDto, RemoteService } from '../remote.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css',
})
export class PaymentComponent {
  order:OrderDto = {
    shippingAddress: '',
    billingAddress: '',
  };
  streetAddress : string = '';
  zipcode: string = '';
  country: string = '';
  city: string = '';
  //orderitems:Array<OrderItemDto> TODO
  constructor(private remoteService: RemoteService) {}
  checkOut(){
    //REMOVE THIS LATER?(WHAT IS OUR SHIPPING ADDRESS?)
    this.order.shippingAddress = "13501 Nantucket Place Bakersfield,CA"
    this.order.billingAddress = this.streetAddress + this.city + this.zipcode + this.country;
    console.log(this.order);
    //this.remoteService.
    
  }
}
