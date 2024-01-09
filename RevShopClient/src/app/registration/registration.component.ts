import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountDto, RemoteService } from '../remote.service';

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
    if(this.user.username == "" || this.user.password == "" || this.user.email == "" || this.user.phone == "")
    {
      this.message = "please make sure to fill out all parts of the form";
      return;
    }
    this.remoteService.saveUser(this.user).subscribe({
        next: data => {
          console.log(data);
        },
        error: e => console.log(e)
    })
  }
}
