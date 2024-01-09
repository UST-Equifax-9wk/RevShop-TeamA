import { Component } from '@angular/core';
import { RemoteService, NewMessageDto } from '../remote.service';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Subject } from 'rxjs';
import { error } from 'console';

@Component({
  selector: 'app-send-message-button',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './send-message-button.component.html',
  styleUrl: './send-message-button.component.css'
})
export class SendMessageButtonComponent {
  remoteService: RemoteService;

  newMessage: NewMessageDto = {
    recipient:"roman.mazzoni@yahoo.com", 
    subject:"test email",
    message:"This is the email body"
  }

  constructor(remoteService: RemoteService){
    this.remoteService = remoteService;
  }

  sendEmail(){
    this.remoteService.sendEmail(this.newMessage).subscribe({
      next:(data)=>{
        alert("Email was sent")
        console.log(data)
      },
      error:(error:HttpErrorResponse) =>{
        alert("Email did not send")
        console.log(error.error)
      }
    })
  }
}
