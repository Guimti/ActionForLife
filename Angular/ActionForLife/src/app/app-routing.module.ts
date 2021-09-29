import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
const routes: Routes = [
  {path: '', redirectTo:'login', pathMatch: 'full'},
  {path:"login", component:LoginComponent},
  {path:"home", component:HomeComponent},
  {path:"registration",component:RegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
