import { Component } from '@angular/core';
import { RemoteService, NewProductDto } from '../remote.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register-item',
  standalone: true,
  imports: [],
  templateUrl: './register-item.component.html',
  styleUrl: './register-item.component.css'
})
export class RegisterItemComponent {
  remoteService:RemoteService
  sellerName:string
  newProduct:NewProductDto = {
    name:"",
    description : "",
    category : "",
    price: 0,
    inventoryCount: 0
  }
  constructor(remoteService:RemoteService){
    this.remoteService = remoteService
    this.sellerName = ""
  }

  registerProduct(){
    this.remoteService.registerNewProduct(this.newProduct,this.sellerName).subscribe({
      next:(data)=>{
        alert("Registered a new product to seller account: " + this.sellerName + "!")
        console.log(data)
    }, 
      error:(error:HttpErrorResponse) =>{
        alert("Error: could not register that product")
        console.log(error.error)

      }

    })
  }
}
