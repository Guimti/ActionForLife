import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { CategoryModel } from '../Model/CategoryModel';
import { CategoryService } from '../service/category.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  categoryList: CategoryModel[]

  constructor(
    private router: Router,
    private categoryService: CategoryService
  ) { }

  ngOnInit() {
    window.scroll(0,0)
    /* if(environment.token == '') {
      this.router.navigate(['/login'])
    } */
    
    this.getAllCategories()
  }

  getAllCategories() {
    this.categoryService.getAllCategory().subscribe((resp: CategoryModel[]) => {
      this.categoryList = resp
    })
  }

  exit() {
    this.router.navigate(['/login'])
    environment.token = ''
    environment.address = ''
    environment.email = ''
    environment.lastName = ''
    environment.name = ''
    environment.password = ''
    environment.id = 0
    environment.photo = ''
  }
}
