import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable , catchError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RemoteService {

  httpClient: HttpClient;
  baseUrl: String;
  httpOptions ={
    observe: 'response',
    withCredentials:true,
    headers: new HttpHeaders({'Content-Type':'application/json'})
  }
  constructor(httpClient: HttpClient) { 
    this.httpClient = httpClient;
    this.baseUrl = "http://localhost:8080"
  }
  sendEmail(message:NewMessageDto){
    return this.httpClient.post(this.baseUrl + "/sendEmail", JSON.stringify(message),{
      observe: 'response',
      withCredentials: true ,
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
  }
}
export interface NewMessageDto{
  recipient:string
  subject:string
  message:string
}