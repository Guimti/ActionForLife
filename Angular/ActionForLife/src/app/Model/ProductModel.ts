import { CategoryModel } from "./CategoryModel"

export class ProductModel{
    public idProduct: number
    public name: string
    public brand: string
    public description: string
    public price: string
    public photo: string
    public categoryProduct:CategoryModel

    public quantidade: number
    public valorParcial: number
}