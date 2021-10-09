import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { ProductModel } from '../Model/ProductModel';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllProducts(): Observable<ProductModel[]> {
    return this.http.get<ProductModel[]>('https://action-forlife.herokuapp.com/product/all', this.token)
  }

  postProduct(product: ProductModel): Observable<ProductModel> {
    return this.http.post<ProductModel>('https://action-forlife.herokuapp.com/product/add', product, this.token)
  }
}
