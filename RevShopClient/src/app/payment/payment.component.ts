import { Component } from '@angular/core';
import { NewProductDto, OrderDto, OrderItemDto, RemoteService } from '../remote.service';
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
    orderStatus: ''
  };
  streetAddress : string = '';
  zipcode: string = '';
  country: string = '';
  city: string = '';
  arr: Array<NewProductDto> = [];
  //orderitems:Array<OrderItemDto> 
  constructor(private remoteService: RemoteService) {
    remoteService.getAllProducts().subscribe({
      next: data =>{
        let tempProduct : NewProductDto =
        {
          name: '',
          description: '',
          category: '',
          price: 0,
          inventoryCount: 0
        };
        console.log(data);
        console.log(data.body);
        for(let val in data.body)
        {
          console.log((data.body as any)[val]);
          console.log(val);
          Object.assign(tempProduct,(data.body as any)[val])
          this.arr.push(tempProduct)
          console.log(this.arr);
        }
      },
      error: e => console.log(e)
    });
  }
  checkOut(){
    //REMOVE THIS LATER?(WHAT IS OUR SHIPPING ADDRESS?)
    this.order.shippingAddress = "13501 Nantucket Place Bakersfield,CA"
    this.order.billingAddress = this.streetAddress + " " + this.city + " " + this.zipcode + " " + this.country;

    this.order.orderStatus = "PENDING";
    console.log(this.order);
    this.remoteService.saveOrder(this.order).subscribe({
      next: data=> {
        Object.assign(this.order,data.body);
        console.log(this.order);
      },
      error: e => console.log(e)
    });
    
  }
}
