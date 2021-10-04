import { CategoryModel } from "./CategoryModel"

export class ProductModel{
    public idProduct: number
    public namee: string
    public brand:string
    public description:string
    public price:string
    public categoryProduct:CategoryModel
}