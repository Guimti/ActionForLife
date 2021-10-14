import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { ProductModel } from '../Model/ProductModel';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {
  produto: ProductModel = new ProductModel
  carrinho: ProductModel[]
  vParcial: number
  vTotal: number
  vazio: string
  quant: number
  carrinhoOb = {
    valor: 0}

  constructor(
    private authService: AuthService,
    private router: Router
    
  ) { }

  ngOnInit(){
    window.scroll(0,0)
    if (environment.token == "") {
     alert('É PRECISO ESTAR LOGADO')
      this.router.navigate(["/login"])
    }

    this.exibirCarrinho()
    // this.total()
  }

  exibirCarrinho() {
    const localS = localStorage['carrinho']
    if (localS.length > 0) {
      this.carrinho = localS ? JSON.parse(localS) : []
    } else {
      this.vazio = "O Carrinho está vazio"
      this.vTotal = 0
    }
  }

  // total() {
  //   this.vTotal = 0
  //   let dadosProd = []
  //   dadosProd = JSON.parse(localStorage.getItem('carrinho') || '{}')
  //   dadosProd.forEach((i) => {
  //     this.carrinhoOb = {
  //       valor: i.valorParcial
  //     }


  //     this.vTotal = this.carrinhoOb.valor + this.vTotal
  //   })
  //   return this.vTotal.toFixed(2)
  // }
}
