import { Routes } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { PaymentComponent } from './payment/payment.component';
import { RegisterItemComponent } from './register-item/register-item.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';

export const routes: Routes = [
    {path : "login", component:LoginComponent},
    {path : "register", component:RegistrationComponent},
    {path : "dashboard", component:DashboardComponent},
    {path : "addProduct", component:RegisterItemComponent},
    {path : "payment", component:PaymentComponent}
];
