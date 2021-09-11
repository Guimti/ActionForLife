package com.actionforlife.ActionForLife.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.actionforlife.ActionForLife.Model.UserModel;
import com.actionforlife.ActionForLife.Model.Util.UserLogin;
import com.actionforlife.ActionForLife.Repository.UserRepository;
import com.actionforlife.ActionForLife.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private @Autowired UserRepository repository;
	private @Autowired UserService service;

	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> getAll() {
		List<UserModel> listObject = repository.findAll();

		if (listObject.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listObject);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<UserModel> findById(@PathVariable Long idUser) {

		return repository.findById(idUser).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<UserModel>> findByName(@PathVariable(value = "name") String name) {
		List<UserModel> listObject = repository.findAllByNameContainingIgnoreCase(name);
		if (listObject.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listObject);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody UserModel newUser) {
		Optional<Object> objectOptional = service.registerUser(newUser);

		if (objectOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objectOptional.get());
		}
	}

	@PutMapping("/authorize")
	public ResponseEntity<UserLogin> authorization(@RequestBody Optional<UserLogin> user) {
		return service.authorize(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PutMapping("/update")
	public ResponseEntity<UserModel> updateUser(@Valid @RequestBody UserModel updatedUser) {
		return ResponseEntity.ok(repository.save(updatedUser));
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable(value = "id") Long idUser) {
		repository.deleteById(idUser);
	}
}