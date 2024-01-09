import { Component } from '@angular/core';
import { OrderDto, OrderItemDto, RemoteService } from '../remote.service';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css',
})
export class PaymentComponent {
  order:OrderDto = {
    shippingAddress: '',
    billingAddress: '',
    timestamp: '',
  }
  //orderitems:Array<OrderItemDto> TODO
  constructor(private remoteService: RemoteService) {}
  checkOut(){

  }
}
