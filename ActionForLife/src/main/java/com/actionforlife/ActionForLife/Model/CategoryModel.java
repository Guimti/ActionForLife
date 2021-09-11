package com.actionforlife.ActionForLife.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class CategoryModel {

	/**
	 * Classe utilizada como Entidade no Banco de dados para Categoria, a mesma
	 * possui atributos que seram colunas no banco com titulo: Planta, Vestuario,
	 * Bijuteria.
	 * 
	 * Descrição para categoria / adicionar mais atributos.
	 * 
	 * @author ActionForLife
	 * @since 1.0
	 */

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idCategory;
	private @NotBlank String type;
	private String description;
	private @NotBlank String relevancy;

	@OneToMany(mappedBy = "productCategory", cascade = CascadeType.REMOVE)
	private List<ProductModel> products = new ArrayList<>();

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}

	public String getDescription() {
		return description;
	}

	public void setDesription(String description) {
		this.description = description;
	}

	public String getRelevancy() {
		return relevancy;
	}

	public void setRelevancy(String relevancy) {
		this.relevancy = relevancy;
	}

}