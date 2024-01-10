import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RemoteService {
  httpClient: HttpClient;
  baseUrl: String;
  httpOptions = {
    observe: 'response',
    withCredentials: true,
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };
  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
    this.baseUrl = 'http://localhost:8080';
  }
  sendEmail(message: NewMessageDto) {
    return this.httpClient.post(
      this.baseUrl + '/sendEmail',
      JSON.stringify(message),
      {
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      }
    );
  }
  registerNewProduct(product: NewProductDto, sellerId: number) {
    return this.httpClient.post(
      this.baseUrl + '/Products/' + sellerId + '/addProduct',
      JSON.stringify(product),
      {
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      }
    );
  }
  getAllProducts() {
    return this.httpClient.get(this.baseUrl + '/Products/getProducts', {
      observe: 'response',
      withCredentials: true,
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    });
  }

  registerNewDiscount(product:NewDiscountDto, productId:number){
    return this.httpClient.post(this.baseUrl + "/Discounts/" + productId + "/newDiscount", JSON.stringify(product),{
      observe: 'response',
      withCredentials: true ,
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }


  saveUser(user: AccountDto) {
    return this.httpClient.post(
      this.baseUrl + '/register',
      JSON.stringify(user),
      {
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      }
    );
  }
  saveOrder(order: OrderDto) {
    return this.httpClient.post(
      this.baseUrl + '/setorder',
      JSON.stringify(order),
      {
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      }
    );
  }
  uploadPicutre(file:FormData){
    return this.httpClient.post(this.baseUrl + "/images/newImage",JSON.stringify(file),{
      observe: 'response',
      withCredentials: true ,
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }
  //Comment to commit and push
}
export interface NewMessageDto {
  recipient: string;
  subject: string;
  message: string;
}
export interface NewProductDto {
  name: string;
  description: string;
  category: string;
  price: number;
  inventoryCount: number;
  seller?:SellerDto;
}
export interface SellerDto{
  account:AccountDto;
  sellerId:number;
}
export interface NewDiscountDto{
  discountPrice:number
  startDate:string
  endDate:string

}

export interface BuyerDto{
  firstname:string
  lastname:string
  account:AccountDto
}
export interface AccountDto {
  username: string;
  password: string;
  email: string;
  phone: string;
  accountType: string;
}
export interface OrderDto {
  orderId?: string;
  shippingAddress: string;
  billingAddress: string;
  timestamp?: string;
  buyer?: BuyerDto;
  orderStatus: string;
}
export interface OrderItemDto {
  order: OrderDto;
  amount: number;
  price: number;
}
