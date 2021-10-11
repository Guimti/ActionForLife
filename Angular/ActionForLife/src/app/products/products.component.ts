import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { ProductModel } from '../Model/ProductModel';
import { ProductService } from '../service/product.service';
import { CategoryModel } from '../Model/CategoryModel';
import { CategoryService } from '../service/category.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  category: CategoryModel = new CategoryModel()
  categoryList: CategoryModel[]
  idCat: number

  product: ProductModel = new ProductModel()
  productsList: ProductModel[]

  constructor(
    private router: Router,
    private productService: ProductService,
    private categoryService: CategoryService
  ) { }

  ngOnInit() {
    window.scroll(0,0)
   /*  if(environment.token == '') {
      this.router.navigate(['/login'])
    } */
    
    this.findAllProducts()
    this.findAllCategories()
  }

  findAllCategories() {
    this.categoryService.getAllCategory().subscribe((resp: CategoryModel[]) => {
      this.categoryList = resp
    })
  }

  findAllProducts() {
    this.productService.getAllProducts().subscribe((resp: ProductModel[]) => {
      this.productsList = resp
    })
  }

  findByIdCategory(id: number) {
    console.log("IdCategoriaaaa: "+ JSON.stringify(id))
    this.categoryService.getByIdCategory(id).subscribe((resp: CategoryModel) => {
      this.category = resp
      console.log("Chegou aqui: "+ JSON.stringify(this.category))
    })
  }

}
