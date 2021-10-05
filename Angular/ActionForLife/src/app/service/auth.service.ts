import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { UserLogin } from '../Model/UserLogin';
import { UserModel } from '../Model/UserModel';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
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

}
