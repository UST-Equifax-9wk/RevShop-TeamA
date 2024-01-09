import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountDto, RemoteService, NewMessageDto } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  user:AccountDto = {
    username:"",
    password:"",
    email:"",
    phone:"",
    accountType:"",
  }
  message = ""
  constructor(private remoteService:RemoteService){ }
  registerAccount(){
    console.log(this.user);
    if(this.user.username == "" || this.user.password == "" || this.user.email == "" || this.user.phone == "")
    {
      this.message = "please make sure to fill out all parts of the form";
      return;
    }
    this.remoteService.saveUser(this.user).subscribe({
        next: data => {
          console.log(data);
          console.log("Attempting to send email...");
          this.sendRegistrationMessage()
        },
        error: e => console.log(e)
    })
  }
  sendRegistrationMessage(){
    let newMessage:NewMessageDto = {
      recipient:this.user.email,
      subject:"Your new RevShop account!",
      message:"You have created a new RevShop account with the username: " + this.user.username
    }

    this.remoteService.sendEmail(newMessage).subscribe({
      next:(data)=>{
        console.log(data)
      },
      error:(error:HttpErrorResponse) =>{
        console.log(error.error)
      }
    })
  }
}
