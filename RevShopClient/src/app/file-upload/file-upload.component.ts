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
  remoteService:RemoteService 
  constructor(remoteService:RemoteService ){
    this.remoteService = remoteService
  }
 
  onFileSelected(event:any) {

    const file:File = event.target.files[0];

    if (file) {

        this.fileName = file.name;

        const formData = new FormData();

        formData.append("thumbnail", file);

        this.remoteService.uploadPicutre(formData).subscribe({
          next:(data)=>{
            console.log(data)
          },
          error:(error:HttpErrorResponse) =>{
            console.log(error.error)
          }
        })
      }
    }
}

