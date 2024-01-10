import { Component } from '@angular/core';
import { RemoteService, NewDiscountDto} from '../remote.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register-discount',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register-discount.component.html',
  styleUrl: './register-discount.component.css'
})
export class RegisterDiscountComponent {
  remoteService:RemoteService 
  productId:number
  dullButton:boolean
  newDiscount:NewDiscountDto = {
    discountPrice:0,
    startDate:"",
    endDate:""
  }
  constructor(remoteService:RemoteService){
    this.remoteService = remoteService
    this.productId = 0
    this.dullButton = false
  }
  registerProduct(){
    this.remoteService.registerNewDiscount(this.newDiscount,this.productId).subscribe({
      next:(data)=>{
        alert("Registered a new product discount")
        console.log(data)
    }, 
      error:(error:HttpErrorResponse) =>{
        alert("Error: could not register that product discount")
        console.log(error.error)
      }
    })
  }
}
