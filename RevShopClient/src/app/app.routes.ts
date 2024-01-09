import { Routes } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { PaymentComponent } from './payment/payment.component';
import { RegisterItemComponent } from './register-item/register-item.component';

export const routes: Routes = [
    {path : "register", component:RegistrationComponent},
    {path : "addProduct", component:RegisterItemComponent},
    {path : "payment", component:PaymentComponent}
];
