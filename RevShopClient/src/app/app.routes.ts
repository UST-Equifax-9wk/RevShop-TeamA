import { Routes } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { PaymentComponent } from './payment/payment.component';

export const routes: Routes = [
    {path : "register", component:RegistrationComponent},
    {path : "payment", component:PaymentComponent}
];
