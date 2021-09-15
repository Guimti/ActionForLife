package com.actionforlife.ActionForLife.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actionforlife.ActionForLife.Model.ProductModel;
import com.actionforlife.ActionForLife.Repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository repository;

	@GetMapping("/all")
	public ResponseEntity<List<ProductModel>> getAll() {
		List<ProductModel> listObject = repository.findAll();

		if (listObject.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listObject);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<ProductModel> findById(@PathVariable(value = "id") Long idProduct) {

		return repository.findById(idProduct).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<ProductModel>> findByName(@PathVariable(value = "name") String name) {
		List<ProductModel> listObject = repository.findAllByNameContainingIgnoreCase(name);
		if (listObject.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listObject);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ProductModel> addProduct(@Valid @RequestBody ProductModel newProduct) {
		return ResponseEntity.ok(repository.save(newProduct));
	}

	@PutMapping("/update")
	public ResponseEntity<ProductModel> updateProduct(@Valid @RequestBody ProductModel updateProduct) {
		return ResponseEntity.status(201).body(repository.save(updateProduct));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long idProduct) {
		Optional<ProductModel> productId = repository.findById(idProduct);

		if(productId.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			repository.deleteById(idProduct);
			return ResponseEntity.status(200).build();
		}		
	}	

}
