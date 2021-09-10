package com.actionforlife.ActionForLife.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CategoriaModel {
	
	/**
	 * Classe utilizada como Entidade no Banco de dados para Categoria, a mesma
	 * possui atributos que seram colunas no banco com titulo: Planta, Vestuario, Bijuteria. 
	 * 
	 * Descrição para categoria / adicionar mais atributos.
	 * @author ActionForLife
	 * @since 1.0
	 */
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idCategoria;
	private String tipo;
	private String desricao;
	private String relevancia;
	
	
	@OneToMany(mappedBy = "categoriaProduto", cascade = CascadeType.REMOVE)
	private List<ProdutoModel> produtos = new ArrayList();
	
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<ProdutoModel> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}
	public String getDesricao() {
		return desricao;
	}
	public void setDesricao(String desricao) {
		this.desricao = desricao;
	}
	public String getRelevancia() {
		return relevancia;
	}
	public void setRelevancia(String relevancia) {
		this.relevancia = relevancia;
	}	
	
	
	
	
	
}
