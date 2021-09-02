package com.actionforlife.ActionForLife.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ProdutoModel {

	/**
	 * Classe utilizada como Entidade no Banco de dados para Produto, a mesma possui
	 * atributos que seram colunas no banco com titulo: Nome, Marca, Descrição,
	 * Preço.
	 * 
	 * @author ActionForLife
	 * @since 1.0
	 */

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long idProduto;
	private @NotBlank String nome;
	private String marca;
	private String descricao;
	private double preco;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@JsonIgnoreProperties({ "produtos" })
	private CategoriaModel categoriaProduto;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({ "meusProdutos" })
	private UsuarioModel comprador;

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public CategoriaModel getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaModel categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public UsuarioModel getComprador() {
		return comprador;
	}

	public void setComprador(UsuarioModel comprador) {
		this.comprador = comprador;
	}
}
