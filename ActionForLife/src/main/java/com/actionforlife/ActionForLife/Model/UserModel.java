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
public class UserModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUser;
	private @NotBlank String name;
	private @NotBlank String email;
	private @NotBlank @Size(min = 5) String password;
	
	@OneToMany(mappedBy = "buyer", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "buyer" })
	private List<ProductModel> myProducts = new ArrayList<>();

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ProductModel> getMyProducts() {
		return myProducts;
	}

	public void setMyProducts(List<ProductModel> myProducts) {
		this.myProducts = myProducts;
	}

}
