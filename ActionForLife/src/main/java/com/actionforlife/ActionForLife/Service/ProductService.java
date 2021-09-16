package com.actionforlife.ActionForLife.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actionforlife.ActionForLife.Model.ProductModel;
import com.actionforlife.ActionForLife.Repository.ProductRepository;

@Service
public class ProductService {
	private @Autowired ProductRepository repository;

	public Optional<ProductModel> UpdateProduct(ProductModel productToChange) {
		return repository.findById(productToChange.getIdProduct()).map(ExistingProduct -> {
			ExistingProduct.setIdProduct(productToChange.getIdProduct());
			return Optional.ofNullable(repository.save(ExistingProduct));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
