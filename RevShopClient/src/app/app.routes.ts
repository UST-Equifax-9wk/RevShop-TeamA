import { Routes } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { PaymentComponent } from './payment/payment.component';
import { RegisterItemComponent } from './register-item/register-item.component';
import { FileUploadComponent } from './file-upload/file-upload.component';

export const routes: Routes = [
    {path : "register", component:RegistrationComponent},
    {path : "addProduct", component:RegisterItemComponent},
    {path : "payment", component:PaymentComponent},
    {path : "fileUpload", component:FileUploadComponent},
];
