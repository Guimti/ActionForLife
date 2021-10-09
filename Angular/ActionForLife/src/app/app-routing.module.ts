import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProductsComponent } from './products/products.component';
import { CategoryEditComponent } from './edit/category-edit/category-edit.component';
import { CategoryDeleteComponent } from './delete/category-delete/category-delete.component';

const routes: Routes = [
  {path: '', redirectTo:'login', pathMatch: 'full'},
  {path: "login", component: LoginComponent},
  {path: "home", component: HomeComponent},
  {path: "registration", component: RegistrationComponent},
  {path: "products", component: ProductsComponent},
  {path: "category-edit/:id", component: CategoryEditComponent},
  {path: "category-delete/:id", component: CategoryDeleteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
