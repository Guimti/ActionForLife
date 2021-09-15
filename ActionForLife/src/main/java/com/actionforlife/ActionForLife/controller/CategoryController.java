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

import com.actionforlife.ActionForLife.Model.CategoryModel;
import com.actionforlife.ActionForLife.Repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository repository;

	@GetMapping("/all")
	public ResponseEntity<List<CategoryModel>> getAll() {

		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<CategoryModel> getById(@PathVariable(value = "id") Long id) {

		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/type/{type}")
	public ResponseEntity<List<CategoryModel>> findByType(@PathVariable(value = "type") String type) {
		List<CategoryModel> listObject = repository.findAllByTypeContainingIgnoreCase(type);
		if (listObject.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listObject);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<CategoryModel> save(@Valid @RequestBody CategoryModel toSave) {
		return ResponseEntity.ok(repository.save(toSave));
	}

	@PutMapping("/update")
	public ResponseEntity<CategoryModel> update(@Valid @RequestBody CategoryModel updateCategory) {
		return ResponseEntity.status(201).body(repository.save(updateCategory));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deletarId(@PathVariable(value = "Id") Long idCategory) {
		Optional<CategoryModel> categoryId = repository.findById(idCategory);

		if(categoryId.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			repository.deleteById(idCategory);
			return ResponseEntity.status(200).build();
		}		
	}

}
