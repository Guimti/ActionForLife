package com.actionforlife.ActionForLife.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ProductModel {

	/**
	 * Classe utilizada como Entidade no Banco de dados para Produto, a mesma possui
	 * atributos que seram colunas no banco com titulo: Nome, Marca, Descrição,
	 * Preço.
	 * 
	 * @author ActionForLife
	 * @since 1.0
	 */

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long idProduct;
	private @NotBlank String name;
	private String brand;
	private String description;
	private @NotNull Double price;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonIgnoreProperties({ "products" })
	private CategoryModel categoryProduct;

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public CategoryModel getCategoryProduct() {
		return categoryProduct;
	}

	public void setCategoryProduct(CategoryModel categoryProduct) {
		this.categoryProduct = categoryProduct;
	}

	
}
