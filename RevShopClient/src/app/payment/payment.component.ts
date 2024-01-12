import { Component } from '@angular/core';
import { AccountDto, BuyerDto, NewProductDto, OrderDto, OrderItemDto, RemoteService } from '../remote.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CookieService } from 'ngx-cookie-service';

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
  account: AccountDto = {
    accountId: -1,
    username: '',
    password: '',
    email: '',
    phone: '',
    accountType: ''
  };
  buyer: BuyerDto = {
    firstname: '',
    lastname: ''
  }
  arr: Array<NewProductDto> = [];
  //orderitems:Array<OrderItemDto> 
  constructor(private remoteService: RemoteService, private cookieService: CookieService) {
    //TODO: maybe change so that the user that comes back doesnt contain the encrypted password
    this.remoteService.getUser(this.remoteService.decodeToken(this.cookieService.get('token')).username).subscribe({
      next:data =>{
        console.log(data.body);
        Object.assign(this.account,data.body);
        console.log(this.account);
        this.remoteService.getBuyer(this.account.accountId!).subscribe({
          next: data =>{
            console.log(data.body);
            Object.assign(this.buyer,data.body);
            console.log(this.buyer);
          }
        });
      }
    });
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
    //REMOVE THIS LATER?(WHAT IS OUR SHIPPING ADDRESS? THIS SHOULD BE MOVED TO ORDERITEM?)
    console.log(this.arr[0].seller)
    this.order.billingAddress = this.streetAddress + " " + this.city + " " + this.zipcode + " " + this.country;
    console.log(this.remoteService.decodeToken(this.cookieService.get('token')));
    this.order.orderStatus = "PENDING";
    console.log(this.order);
    console.log(this.buyer);
    this.remoteService.saveOrder(this.order,this.buyer.id!).subscribe({
      next: data=> {
        Object.assign(this.order,data.body);
        console.log(this.order);
      },
      error: e => console.log(e)
    });
    
  }
}
