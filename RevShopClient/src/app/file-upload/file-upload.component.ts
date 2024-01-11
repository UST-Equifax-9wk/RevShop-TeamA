import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RemoteService } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './file-upload.component.html',
  styleUrl: './file-upload.component.css'
})
export class FileUploadComponent {
  fileName = '';
  file!: File;
  remoteService:RemoteService 
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  constructor(remoteService:RemoteService ){
    this.remoteService = remoteService

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
        this.remoteService.uploadPicutre(formData).subscribe({
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
        alert("Please upload a file first!")
      }
    }

    getImage(){
      console.log("sending request to get image")
      this.remoteService.downloadImage(this.fileName).subscribe({
        next:(data)=>{
          this.retrieveResonse = data;
          this.base64Data = this.retrieveResonse.body.imageData;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data
          console.log("image retrived!")
          //console.log(this.retrieveResonse)
          //console.log(this.base64Data)
          //console.log(this.retrievedImage)
        },
        error:(error:HttpErrorResponse) =>{
          console.log(error.error)
        }
      })
    }
}

