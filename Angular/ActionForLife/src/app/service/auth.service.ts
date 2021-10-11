import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { UserLogin } from '../Model/UserLogin';
import { UserModel } from '../Model/UserModel';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private router: Router
    ) { }

  login(userLogin: UserLogin): Observable<UserLogin>{
    return this.http.put<UserLogin>('https://action-forlife.herokuapp.com/user/authorize', userLogin)
  }

  register(userModel: UserModel): Observable<UserModel>{
    return this.http.post<UserModel>('https://action-forlife.herokuapp.com/user/register', userModel)
  }

  logado() {
    let ok: boolean = false

    if(environment.token != ''){
      ok = true
    }

    return ok
  }  

  menuRodapeOff(){
    let ok: boolean = true
    if(this.router.url == '/login' || this.router.url == '/registration' || this.router.url == "/cadastrar" || this.router.url.indexOf("user-edit") == 1){
      ok = false
    }
    return ok
  }

}
