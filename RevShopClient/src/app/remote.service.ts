import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class RemoteService {
  httpClient: HttpClient;
  baseUrl: String;
  cookieService: CookieService;
  httpOptions = {
    observe: 'response',
    withCredentials: true,
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };
  constructor(httpClient: HttpClient, cookieService: CookieService) {
    this.httpClient = httpClient;
    this.cookieService = cookieService;
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

  login(authDto: LoginDto): Observable<HttpResponse<Object>> {
    return this.httpClient.post(this.baseUrl + "/login", JSON.stringify(authDto),
    {
      observe: 'response', 
      withCredentials: true ,
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })}
    )
  }
  
  decodeToken(token: string) {
    const payload = token.split('.')[1];
    const decodedPayload = atob(payload);
    const payloadObj = JSON.parse(decodedPayload);
    const uName = payloadObj.sub;
    const accountType = payloadObj.accountType[0].authority;
    const specificId = payloadObj.specificId;
    console.log("uName", uName);
    console.log("accountType", accountType);
    console.log("specificId", specificId); 
    return {username: uName, accountType: accountType, specificId: specificId};
  }
  getUser(username: string){
    return this.httpClient.get(
      this.baseUrl + '/' + username,
      {
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      }
    );
  }
  getBuyer(id:number){
    let params : HttpParams = new HttpParams().set('id',id);
    return this.httpClient.get(this.baseUrl + '/getBuyer',{
      observe: 'response',
      withCredentials: true,
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      params:params
    });
  }
  saveUser(user: AccountDto,address:string,firstname:string,lastname:string) {
    let params : HttpParams = new HttpParams().set('address',address).set('firstname',firstname).set('lastname',lastname);
    return this.httpClient.post(
      this.baseUrl + '/register',
      JSON.stringify(user),
      {
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
        params:params
      }
    );
  }
  saveOrder(order: OrderDto,id:number) {
    let params : HttpParams = new HttpParams().set('id',id);
    return this.httpClient.post(
      this.baseUrl + '/setorder',
      JSON.stringify(order),
      {
        observe: 'response',
        withCredentials: true,
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
        params:params
      }
    );
  }

  getCart(buyerId: number) {
    return this.httpClient.get(this.baseUrl + `/buyer/${buyerId}/cart`, {
      observe: 'response',
      withCredentials: true ,
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    });
  }

  uploadPicutre(file:FormData, productId:number){
    return this.httpClient.post(this.baseUrl + "/images/" + productId + "/newImage",file,{
      reportProgress: true,
      withCredentials: true,
      observe:'events'
    })
  }

  addCard(cardDto: CardDto, accountId: string) : Observable<HttpResponse<Object>> {
    const token = this.cookieService.get('token');
    const endpoint = `/cards/add?accountId=${accountId}`
    return this.httpClient.post(this.baseUrl + endpoint , JSON.stringify(cardDto),
    {
      observe: 'response', 
      withCredentials: true,
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    })});
  }

  getCardDetails(accountId: string) : Observable<any> {
    const token = this.cookieService.get('token');
    console.log('aId',accountId);
    return this.httpClient.get(this.baseUrl + `/cards/all?accountId=${accountId}`, {
      observe: 'response', 
      withCredentials: true,
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    })});
  }

  getUserInfo(username: string) : Observable<HttpResponse<Object>> {
    const token = this.cookieService.get('token');
    return this.httpClient.get(this.baseUrl + `/${username}`, {
      observe: 'response', 
      withCredentials: true,
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    })});
  }

  downloadImage(imageName:string){
  return this.httpClient.get(this.baseUrl + '/images/' + imageName,
       {
          observe: 'response',
          withCredentials: true,
          headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
        }
      );
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
  account?:AccountDto
  id?:number
}
export interface AccountDto {
  accountId?: number;
  username: string;
  password: string;
  email: string;
  phone: string;
  accountType: string;
}
export interface CartItemDto {
  cartItemId: number,
  productId: number,
  name: string,
  quantity: number
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
export interface LoginDto {
  username: String
  password: String
}
export interface CardDto {
  cardNumber: string
  expirationDate: string
  cardHolderName: string
  cardType: string
}