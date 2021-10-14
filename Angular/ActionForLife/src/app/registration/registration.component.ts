import { Component, OnInit } from '@angular/core';
import { EmailValidator } from '@angular/forms';
import { Router } from '@angular/router';
import { UserModel } from '../Model/UserModel';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  userModel: UserModel = new UserModel()
  confirmPassword1: string
  code: string
  user: UserModel = new UserModel()
  email:UserModel = new UserModel

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
  }

  confirmPassword(event: any) {
    this.confirmPassword1 = event.target.value
  }

  typeUser(event: any) {
    this.userModel.type = event.target.value
    console.log("categorias: "+ JSON.stringify(this.userModel.type))
  }

  register() {
    if (this.userModel.password != this.confirmPassword1) {
      alert("As senhas estão incorretas.")
    } else {
      if (this.userModel.type == "Administrador" && this.code == "xxx") {
        this.authService.register(this.userModel).subscribe((resp: UserModel) => {
          this.userModel = resp
          this.router.navigate(['/login'])
          alert("Usuário cadastrado com sucesso!")
        })
      } else if (this.userModel.type == "Normal") {
        this.authService.register(this.userModel).subscribe((resp: UserModel)=>{
          this.userModel = resp
          this.router.navigate(["/login"])
          alert("Usuário cadastrado com sucesso!")
        })
        } else {
        alert("Dados incorretos, por favor corrigir.")
        this.router.navigate(['/registration'])
      }

     }
  
    //  if(error.status==400){
    //   alert("Este Email ja existe. Por favor tento um email diferente")
    // }
  }
}
