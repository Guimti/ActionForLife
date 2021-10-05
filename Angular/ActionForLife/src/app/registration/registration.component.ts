import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from '../Model/UserModel';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  userModel: UserModel = new UserModel
  confirmPassword1: string

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    window.scroll(0,0)
  }

  confirmPassword(event: any) {
    this.confirmPassword1 = event.target.value
  }

  register() {
    
    if(this.userModel.password != this.confirmPassword1){
      alert("As senhas estão incorretas.")
    }else{
      this.authService.register(this.userModel).subscribe((resp: UserModel) => {
        this.userModel = resp
        this.router.navigate(['/login'])
        alert("Usuário cadastrado com sucesso!")
      })
    }
  }

}
