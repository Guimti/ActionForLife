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
	 * @author ActionForLife
	 * @since 1.0
	 */
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long idCategoria;
	private String planta;
	private String vestuario;
	private String bijuteria;
	
	@OneToMany(mappedBy = "categoriaProduto", cascade = CascadeType.REMOVE)
	private List<ProdutoModel> produtos = new ArrayList();
	
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getVestuario() {
		return vestuario;
	}
	public void setVestuario(String vestuario) {
		this.vestuario = vestuario;
	}
	public String getBijuteria() {
		return bijuteria;
	}
	public void setBijuteria(String bijuteria) {
		this.bijuteria = bijuteria;
	}
	public List<ProdutoModel> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}	
}
