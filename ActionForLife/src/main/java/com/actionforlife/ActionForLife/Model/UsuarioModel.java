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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class UsuarioModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUsuario;
	private @NotBlank String nome;
	private @NotBlank String email;
	private @NotBlank @Size(min = 5) String senha;
	@OneToMany(mappedBy = "comprador", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "comprador" })
	private List<ProdutoModel> meusProdutos = new ArrayList<>();

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<ProdutoModel> getMeusProdutos() {
		return meusProdutos;
	}

	public void setMeusProdutos(List<ProdutoModel> meusProdutos) {
		this.meusProdutos = meusProdutos;
	}

}
