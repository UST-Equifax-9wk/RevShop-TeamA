import { Component } from '@angular/core';
import { RemoteService, NewProductDto, NewMessageDto } from '../remote.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register-item',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register-item.component.html',
  styleUrl: './register-item.component.css'
})
export class RegisterItemComponent {
  remoteService:RemoteService
  //TODO: pull seller id and email for these 2 vairables
  sellerId:number
  sellerEmail:string
  fileName = '';
  file!: File;
  dullButton:boolean
  productId:number
  newProduct:NewProductDto = {
    name:"",
    description : "",
    category : "",
    price: 0,
    inventoryCount: 0
  
  }
  constructor(remoteService:RemoteService){
    this.remoteService = remoteService
    //TODO get the seller id from cookie 
    this.sellerId = 1
    this.dullButton = false
    this.sellerEmail = ""
    this.productId = 1
  }

  registerProduct(){
    this.remoteService.registerNewProduct(this.newProduct,this.sellerId).subscribe({
      next:(data)=>{
        alert("Registered a new product to seller account: " + this.sellerId + "!")
        console.log(data)
        this.sendNewProductMessage()
        this.UploadFile()
    }, 
      error:(error:HttpErrorResponse) =>{
        alert("Error: could not register that product")
        console.log(error.error)
      }
    })
  }
  sendNewProductMessage(){
    let newMessage:NewMessageDto = {
      recipient:this.sellerEmail,
      subject:"Added a new product to your RevShop!",
      message:"You have added a new product to your shop with the name " + this.newProduct.name + " with a price of " + this.newProduct.price + " and description: " + this.newProduct.description
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
  onFileSelected(event:any) {

    this.file= event.target.files[0];

    if (this.file) {

        this.fileName = this.file.name;
    }
  }

    UploadFile(){
      if (this.file) {
        const formData = new FormData();

        formData.append('imageFile', this.file, this.fileName);
        
        //console.log(formData);
        this.remoteService.uploadPicutre(formData, this.productId).subscribe({
          next:(data)=>{
            console.log(data)
            console.log("data was sent to server")
          },
          error:(error:HttpErrorResponse) =>{
            console.log(error.error)
          }
        })
      }
      else{
        console.log("There was no image with the product")
      }
    }
}
