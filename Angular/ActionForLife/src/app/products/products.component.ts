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
    if(environment.token == '') {
      this.router.navigate(['/login'])
    }
    
    this.findAllProducts()
    this.getAllCategories()
  }

  getAllCategories() {
    this.categoryService.getAllCategory().subscribe((resp: CategoryModel[]) => {
      this.categoryList = resp
    })
  }

  findAllProducts() {
    this.productService.getAllProducts().subscribe((resp: ProductModel[]) => {
      this.productsList = resp
    })
  }

  findByIdCategory() {
    console.log("IdCategoria: "+ JSON.stringify(this.idCat))
    this.categoryService.getByIdCategory(this.idCat).subscribe((resp: CategoryModel) => {
      console.log("Chegou aqui")
      this.category = resp
    })
  }
}
