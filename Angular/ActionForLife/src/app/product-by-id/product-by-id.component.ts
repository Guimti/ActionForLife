import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { ProductModel } from '../Model/ProductModel';
import { UserModel } from '../Model/UserModel';
import { ProductService } from '../service/product.service';

@Component({
  selector: 'app-product-by-id',
  templateUrl: './product-by-id.component.html',
  styleUrls: ['./product-by-id.component.css']
})
export class ProductByIdComponent implements OnInit {

  product: ProductModel = new ProductModel()
  carrinho: ProductModel[]
  quant: number
  vParcial: number

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    let id = this.route.snapshot.params['id']
    this.findProdById(id)
    console.log(JSON.stringify(id))
  }

  findProdById(id: number) {
    this.productService.getByIdProducts(id).subscribe((resp: ProductModel) => {
      this.product = resp
    })
  }

  process(value: number) {
    value += this.quant;
    if (value < 1) {
      this.quant = 1;
    } else if (value >= (this.product.quantidade - 1)) {
      this.quant = (this.product.quantidade - 1);
    } else {
      this.quant = value;
    }
  }

}
