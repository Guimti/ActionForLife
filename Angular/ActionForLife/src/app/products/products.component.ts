import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { ProductModel } from '../Model/ProductModel';
import { ProductService } from '../service/product.service';
import { CategoryModel } from '../Model/CategoryModel';
import { CategoryService } from '../service/category.service';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  category: CategoryModel = new CategoryModel()
  categoryList: CategoryModel[]
  idCateg: number

  product: ProductModel = new ProductModel()
  productsList: ProductModel[]

  constructor(
    private router: Router,
    private productService: ProductService,
    public authService: AuthService,
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
    this.categoryService.getByIdCategory(id).subscribe((resp: CategoryModel) => {
      this.category = resp
    })
  }

  findByIdProduct(id: number) {
    this.productService.getByIdProducts(id).subscribe((resp: ProductModel) => {
      this.product = resp
      console.log("IdCategoriaaaa: "+ JSON.stringify(this.product))

    })
  }

  updateCategory() {
    this.categoryService.putCategory(this.category).subscribe((resp: CategoryModel) => {
      this.category = resp
      alert('Categoria atualizada com sucesso!')
      this.router.navigate(['/home'])
      this.category = new CategoryModel()
    })
  }

  deleteCategory(id: number) {
    console.log("IdCategoriaaaa: "+ JSON.stringify(id))
    this.categoryService.deleteCategory(id).subscribe(() => {
      alert('Categoria deletada com sucesso!')
      this.router.navigate(['/home'])
    }) 
  }

  updateProduct() {
    this.category.idCategory = this.idCateg
    this.product.categoryProduct = this.category

    this.productService.putProduct(this.product).subscribe((resp: ProductModel) => {
      this.product = resp
      if(environment.production=!("")){
        alert('Produto atualizado com sucesso!')
        this.router.navigate(['/products'])
      }else{
        alert('Produto não atualizado tente novamente!')
        this.router.navigate(['/products'])
      }

    })
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe(() => {
      alert('Produto deletado com sucesso!')
      this.router.navigate(['/products'])
    })
  }

  comprar(){
    if (environment.token == "") {
      alert('É preciso estar logado para comprar')
      this.router.navigate(["/login"])
      }else{
        //criar componente carrinho.
      }
    }


}
