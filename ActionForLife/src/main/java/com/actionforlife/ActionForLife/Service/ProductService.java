package com.actionforlife.ActionForLife.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actionforlife.ActionForLife.Model.ProductModel;
import com.actionforlife.ActionForLife.Repository.ProductRepository;

@Service
public class ProductService {
	private @Autowired ProductRepository repository;


	public Optional<?> updateProduct(ProductModel productToChange) {
		return repository.findById(productToChange.getIdProduct()).map(productExists -> {
			productExists.setName(productToChange.getName());
			productExists.setBrand(productToChange.getBrand());
			productExists.setDescription(productToChange.getDescription());
			productExists.setPrice(productToChange.getPrice());
			productExists.setCategoryProduct(productToChange.getCategoryProduct());
			return Optional.ofNullable(repository.save(productExists));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
